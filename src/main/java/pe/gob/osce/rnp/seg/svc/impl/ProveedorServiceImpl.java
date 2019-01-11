package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvoker;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.PreCorreoDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;
import pe.gob.osce.rnp.seg.svc.EmailService;
import pe.gob.osce.rnp.seg.svc.ProveedorService;
import pe.gob.osce.rnp.seg.utils.Enums;
import pe.gob.osce.rnp.seg.utils.Validador;

import java.util.Optional;


@Service
public class ProveedorServiceImpl extends BaseServiceImpl<ProveedorProcedureInvoker> implements ProveedorService {

    public static final Logger LOGGER = LogManager.getLogger(ProveedorServiceImpl.class);

    private EmailService emailService;

    public ProveedorServiceImpl(ProveedorProcedureInvoker repository, EmailService emailService) {
        super(repository);
        this.emailService = emailService;
    }

    @Override
    public Respuesta<OpcionDTO> obtenerOpciones(String ruc) {
        if(Validador.validRuc(ruc)) {
            boolean existeRuc = repository.validarExistencia(ruc);
            if(existeRuc){
                Optional<OpcionDTO> optOpc= Optional.ofNullable(repository.obtenerOpcionesCambioPassword(ruc));
                if(optOpc.isPresent())
                    return new Respuesta(Enums.ResponseCode.EXITO_GENERICA.get(), 1, optOpc.get());
            }
            LOGGER.info("Resul set length: 0");
            return new Respuesta(Enums.ResponseCode.EMPTY_RESPONSE.get(), 0);
        }
        LOGGER.info("Ruc inválido");
        return new Respuesta(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

    @Override
    public Respuesta<String> obtenerCorreo(String ruc){
        if(Validador.validRuc(ruc)){
            Optional<String> correo = Optional.ofNullable(repository.obtenerCorreo(ruc));
            if(correo.isPresent()){
                return new Respuesta(Enums.ResponseCode.EXITO_GENERICA.get(), 1, correo);
            }
            LOGGER.info("Resul set length: 0");
            return new Respuesta(Enums.ResponseCode.EMPTY_RESPONSE.get(), 0);
        }
        LOGGER.info("Ruc inválido");
        return new Respuesta(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0);
    }

    @Override
    public Respuesta<String> enviarCorreo(PreCorreoDTO preCorreoDTO) {
        try {
            String ruc = String.valueOf(preCorreoDTO.getRuc());
            String correoDestino =  preCorreoDTO.getCorreo();
            if(!Validador.validRuc(ruc))
                return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado no es inválido: "+ruc);
            if(!Validador.validarCorreo(correoDestino))
                return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correoDestino);

            Optional<String> optCodVer = Optional.ofNullable(repository.obtenerCodigoVerificacion(ruc, correoDestino, preCorreoDTO.getIpCliente()));
            if(optCodVer.isPresent()){
                Optional<ContenidoCorreoPOJO> optCorreo = Optional.ofNullable(repository.obtenerContenidoCorreoByTipo(1, ruc, optCodVer.get()));
                if(optCorreo.isPresent()){
                    ContenidoCorreoPOJO contenidoCorreo = optCorreo.get();
                    emailService.enviarCorreoInformativo(contenidoCorreo.getNombreAsunto(), correoDestino,contenidoCorreo.getCuerpo());
                    boolean exitoRegistro = repository.registrarCorreoEnviado(ruc, contenidoCorreo.getAsuntoId(),correoDestino) == "1";
                    if(exitoRegistro)
                        return new Respuesta<>(Enums.ResponseCode.EXITO_GENERICA.get(), 1, "El correo ha sido enviado satisfactoriamente");
                    return new Respuesta<>(Enums.ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "El correo no ha sido enviado pero no ha sido registrado en envió en BD");
                }
            }
            return new Respuesta<>(Enums.ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El código de verificación no ha podido ser generado");
        }catch (Exception ex){
            return new Respuesta<>(Enums.ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "Exception: "+ex.getMessage());
        }
    }
}
