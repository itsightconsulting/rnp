package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvoker;
import pe.gob.osce.rnp.seg.dao.SeguridadProcedureInvokerRepository;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;
import pe.gob.osce.rnp.seg.svc.EmailService;
import pe.gob.osce.rnp.seg.svc.ProveedorService;
import pe.gob.osce.rnp.seg.svc.SeguridadService;
import pe.gob.osce.rnp.seg.utils.Enums.ResponseCode;
import pe.gob.osce.rnp.seg.utils.Validador;

import javax.validation.Valid;
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
                    return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
                }
                LOGGER.info("Código de verificación con tamaño inválido o vacio");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> actualizarClave(Long ruc, String clave, String correoDestino) {
        try {
            if (!Validador.validarUsuario(ruc)) {
                LOGGER.info("Ruc inválido");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Ruc inválido");
            }
            if (!Validador.validarClave(clave)) {
                LOGGER.info("Clave tiene un formato inválido o vacio");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Clave tiene un formato inválido o vacio");
            }

            if (!Validador.validarCorreo(correoDestino)) {
                LOGGER.info("El correo presenta un formato inválido: " + correoDestino);
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: " + correoDestino);
            }

            boolean exito = repository.validarClave(ruc.toString());
            if (exito) {
                exito = repository.actualizarClave(ruc.toString(), clave);
                if (exito) {
                    Optional<ContenidoCorreoPOJO> optCorreo = Optional.ofNullable(proveedorRepository.obtenerContenidoCorreoByTipo(2, ruc.toString(), ""));
                    if (optCorreo.isPresent()) {
                        ContenidoCorreoPOJO contenidoCorreo = optCorreo.get();
                        emailService.enviarCorreoInformativo(contenidoCorreo.getNombreAsunto(), correoDestino, contenidoCorreo.getCuerpo());
                        boolean exitoRegistro = proveedorRepository.registrarCorreoEnviado(ruc.toString(), contenidoCorreo.getAsuntoId(), correoDestino) == "1";
                        if (exitoRegistro)
                            return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "La clave ha sido actualizada satisfactoriamente");
                        LOGGER.info("El correo ha sido enviado pero no ha sido registrado en envió en BD");
                        return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "El correo ha sido enviado pero no ha sido registrado en envió en BD");
                    }
                    LOGGER.info("La clave ha sido actualizada pero el correo no ha sido enviado");
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, "La clave ha sido actualizada pero el correo no ha sido enviado");
                }
                LOGGER.info("Lamentablemente la clave no ha podido ser actualizada. Intentarlo más tarde");
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
            }
            LOGGER.info("Clave tiene un formato inválido o vacio");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
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
                return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }
}
