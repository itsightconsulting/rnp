package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvoker;
import pe.gob.osce.rnp.seg.dao.SeguridadProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.model.jpa.dto.UpdClaveDto;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;
import pe.gob.osce.rnp.seg.svc.EmailService;
import pe.gob.osce.rnp.seg.svc.SeguridadService;
import pe.gob.osce.rnp.seg.utils.Enums.ResponseCode;
import pe.gob.osce.rnp.seg.utils.Validador;

import java.util.Optional;

@Service
public class SeguridadServiceImpl extends BaseServiceImpl<SeguridadProcedureInvokerRepository> implements SeguridadService {

    public static final Logger LOGGER = LogManager.getLogger(SeguridadServiceImpl.class);

    private EmailService emailService;

    private ProveedorProcedureInvoker proveedorRepository;

    public SeguridadServiceImpl(SeguridadProcedureInvokerRepository repository,
                                EmailService emailService,
                                ProveedorProcedureInvoker proveedorRepository){
        super(repository);
        this.emailService = emailService;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public Respuesta<String> validarCodVerificacion(Long ruc, Long codVer) {
        try {
            if(Validador.validarUsuario(ruc)){
                if(Validador.validNumberWithLength(codVer, 6)){
                    boolean exito = repository.validarCodVer(ruc.toString(), codVer.toString());
                    if(exito)
                        return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, "Validación exitosa");
                    LOGGER.info("El código de verificación no se ha encontrado en base de datos");
                    return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El código de verificación no se ha encontrado en base de datos");
                }
                LOGGER.info("El código de verificación tiene tamaño inválido o vacio");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El código de verificación tiene tamaño inválido o vacio");
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Ruc inválido");
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> actualizarClave(UpdClaveDto updClave) {
        try {
            String ruc = updClave.getRuc().toString();
            String correo = updClave.getCorreo();
            if (!Validador.validarUsuario(updClave.getRuc())) {
                LOGGER.info("Ruc inválido");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El usuario debe empezar por 10, 20 o 9");
            }
            if (updClave.getClave().length() < 8) {
                LOGGER.info("Clave tiene un formato inválido o vacio");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "La clave tiene un formato inválido o vacio");
            }

            if (!Validador.validarCorreo(updClave.getCorreo())) {
                LOGGER.info("El correo presenta un formato inválido: " + correo);
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: " + correo);
            }

            if(!repository.validarCodVer(ruc, updClave.getCodVerificacion())){
                LOGGER.info("El código de verificación proporcionado ha cadudado o no es válido: COD-"+updClave.getCodVerificacion());
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El código de verificación proporcionado ha cadudado o no es válido: COD-"+updClave.getCodVerificacion());
            }

            boolean exito = repository.validarClave(ruc);
            if (exito) {
                exito = repository.actualizarClave(ruc, updClave.getClave());
                if (exito) {
                    Optional<ContenidoCorreoPOJO> optCorreo = Optional.ofNullable(proveedorRepository.obtenerContenidoCorreoByTipo(2, ruc, ""));
                    if (optCorreo.isPresent()) {
                        ContenidoCorreoPOJO contenidoCorreo = optCorreo.get();
                        boolean mailEnviado = emailService.enviarCorreoInformativo(contenidoCorreo.getNombreAsunto(), correo, contenidoCorreo.getCuerpo());
                        if(mailEnviado){
                            boolean exitoRegistro = proveedorRepository.registrarCorreoEnviado(ruc, contenidoCorreo.getAsuntoId(), correo).equals("1");
                            if (exitoRegistro)
                                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Su clave ha sido actualizada satisfactoriamente y un correo electrónico de confirmación le ha sido enviado");
                            LOGGER.info("La clave ha sido actualizada y el correo enviado pero el registro del correo en bandeja ha fallado");
                            return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 1, "Su clave ha sido actualizada y un correo de confirmación le ha sido enviado");
                        }
                        LOGGER.info("La clave ha sido actualizada pero el correo de confirmación no ha podido ser enviado por un problema interno");
                        return new Respuesta<>(ResponseCode.EX_MAIL_EXCEPTION.get(), 1, "La clave ha sido actualizada pero el correo de confirmación no ha podido ser enviado por un problema interno");

                    }
                    LOGGER.info("La clave ha sido actualizada pero el correo no ha sido enviado");
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, "La clave ha sido actualizada pero el correo no ha sido enviado");
                }
                LOGGER.info("Lamentablemente la clave no ha podido ser actualizada. Intentarlo más tarde");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Lamentablemente la clave no ha podido ser actualizada. Intentarlo más tarde");
            }
            LOGGER.info("La clave tiene un formato inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "La clave tiene un formato");
        }catch (Exception ex){
            LOGGER.info("Excepción: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> validarIngreso(Long ruc, String clave) {
        try {
            if(Validador.validarUsuario(ruc)){
                boolean exito = repository.validarIngreso(ruc.toString(), clave);
                if(exito)
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, "Autenticación correcta");
                LOGGER.info("Usuario o clave no válida");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Usuario o clave no válida");
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El usuario debe empezar por 9, 10 o 20");
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }
}
