package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvoker;
import pe.gob.osce.rnp.seg.generic.BaseServiceImpl;
import pe.gob.osce.rnp.seg.model.jpa.dto.*;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;
import pe.gob.osce.rnp.seg.svc.EmailService;
import pe.gob.osce.rnp.seg.svc.ProveedorService;
import pe.gob.osce.rnp.seg.utils.Enums.ResponseCode;
import pe.gob.osce.rnp.seg.utils.Validador;

import java.util.List;
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
    public Respuesta<OpcionDTO> obtenerOpciones(Long ruc) {
        try {
            if(Validador.validRuc(ruc)) {
                boolean existeRuc = repository.validarExistencia(ruc.toString());
                if(existeRuc){
                    Optional<OpcionDTO> optOpc= Optional.ofNullable(repository.obtenerOpcionesCambioPassword(ruc.toString()));
                    if(optOpc.isPresent())
                        return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, optOpc.get());
                }
                LOGGER.info("Resul set length: 0");
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0);
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }

    }

    @Override
    public Respuesta<String> obtenerCorreo(Long ruc){
        try {
            if(Validador.validRuc(ruc)){
                Optional<String> correo = Optional.ofNullable(repository.obtenerCorreo(ruc.toString()));
                if(correo.isPresent()){
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, correo);
                }
                LOGGER.info("Resul set length: 0");
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0);
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<List<CorreoRepDTO>> obtenerListadoCorreoRepresentante(Long ruc) {
        try {
            if(Validador.validRuc(ruc)){
                Optional<List<CorreoRepDTO>> lstCorreos = Optional.ofNullable(repository.obtenerListadoCorreoRepresentante(ruc.toString()));
                if(lstCorreos.isPresent()){
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, lstCorreos.get());
                }
                LOGGER.info("Resul set length: 0");
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0);
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
    }

    @Override
    public Respuesta<String> enviarCorreo(PreCorreoDTO preCorreoDTO) {
        try {
            Long ruc = preCorreoDTO.getRuc();
            String correoDestino = preCorreoDTO.getCorreo();
            if(!Validador.validRuc(ruc))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado no es inválido: "+ruc);
            if(!Validador.validarCorreo(correoDestino))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correoDestino);

            Optional<String> optCodVer = Optional.ofNullable(repository.obtenerCodigoVerificacion(ruc.toString(), correoDestino, preCorreoDTO.getIpCliente()));
            if(optCodVer.isPresent()){
                Optional<ContenidoCorreoPOJO> optCorreo = Optional.ofNullable(repository.obtenerContenidoCorreoByTipo(1, ruc.toString(), optCodVer.get()));
                if(optCorreo.isPresent()){
                    ContenidoCorreoPOJO contenidoCorreo = optCorreo.get();
                    emailService.enviarCorreoInformativo(contenidoCorreo.getNombreAsunto(), correoDestino,contenidoCorreo.getCuerpo());
                    boolean exitoRegistro = repository.registrarCorreoEnviado(ruc.toString(), contenidoCorreo.getAsuntoId(),correoDestino) == "1";
                    if(exitoRegistro)
                        return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "El correo ha sido enviado satisfactoriamente");
                    LOGGER.info("El correo ha sido enviado pero no ha sido registrado en envió en BD");
                    return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "El correo ha sido enviado pero no ha sido registrado en envió en BD");
                }
            }
            LOGGER.info("El código de verificación no ha podido ser generado");
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El código de verificación no ha podido ser generado");
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<List<ForaneaProveedorDTO>> obtenerListadoForaneaProveedor(String foranea) {
        try {
            Optional<List<ForaneaProveedorDTO>> optLstForanea = Optional.ofNullable(repository.obtenerListadoForanea(foranea));
            if(optLstForanea.isPresent())
                return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, optLstForanea.get());
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
        LOGGER.info("Se ha devuelto un listado vacio");
        return new Respuesta<>(ResponseCode.EMPTY_RESPONSE.get(), 0, null);
    }

    @Override
    public Respuesta<String> validarDatosIdentificacion(DatosIdentificacionDTO dtsIdentificacion) {
        try{
            if(Validador.validRuc(dtsIdentificacion.getRuc())){
                boolean exito = repository.validarDatosIdentificacion(dtsIdentificacion);
                if(exito)
                    return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Validación satisfactoria");
                LOGGER.info("El correo no ha sido enviado pero no ha sido registrado en envió en BD");
                return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "Validación fallida");
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0);
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> actualizarCorreoExtNoDom(Long ruc, String correo) {
        try {
            if(!Validador.validRuc(ruc))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado no es inválido: "+ruc);
            if(!Validador.validarCorreo(correo))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correo);

            Optional<ProcedureOutputDTO>  optProcOut = Optional.ofNullable(repository.actualizarCorreoExtNoDom(ruc.toString(), correo));
            if(optProcOut.isPresent())
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "El correo se ha actualizado satisfactoriamente");
            LOGGER.info("El correo no ha sido enviado pero no ha sido registrado en envió en BD");
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, optProcOut.get().getMensaje());
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> validarCorreoExtNoDom(String correo) {
        try {
            if(!Validador.validarCorreo(correo))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correo);
            Boolean exito = repository.validarExistenciaCorreoExtNoDom(correo);
            if(exito)
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Validación ");
            LOGGER.info("El correo ingresado no se encuentra en nuestra base de datos");
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo ingresado no se encuentra en nuestra base de datos");
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }
}
