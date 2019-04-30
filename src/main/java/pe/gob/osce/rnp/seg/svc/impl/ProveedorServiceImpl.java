package pe.gob.osce.rnp.seg.svc.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.ProveedorProcedureInvokerRepository;
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
public class ProveedorServiceImpl extends BaseServiceImpl<ProveedorProcedureInvokerRepository> implements ProveedorService {

    public static final Logger LOGGER = LogManager.getLogger(ProveedorServiceImpl.class);

    private static final String MSG_RS_LEN = "Result set length: 0";
    private static final String MSG_RUC_INV = "Ruc inválido: ";
    private static final String MSG_RUC_NO_REG = "Usuario no se encuentra registrado en el RNP";
    private static final String MSG_MAIL_REP_NO_REG = "No se encontró correo electrónico de representante o apoderado registrado en el RNP";
    private static final String MSG_J_RUC_INV = "Ruc inválido";
    private static final String MSG_EXCEP_PREFIX = "Excepción: ";
    private static final String MSG_CORREO_INV = "El correo presenta un formato inválido: ";
    private static final String MSG_CORREO_S_FAIL = "El servicio de envio de correo no se encuentra disponible en este momento. Intentarlo nuevamente más tarde";
    private static final String MSG_CORREO_S_FAIL_LOG = "El servicio de envio de correo no se encuentra disponible en este momento | Metodo: ProveedorServiceImpl.enviarCorreoProvExtNoDom(String correo)";
    private static final String MSG_CONF_CORREO = "Un correo le ha sido enviado con todos los nombres de usuario a los que el correo brindado esta asociado. Por favor revisar su bandeja de correo";
    private static final String MSG_MATCHES_NF = "No tenemos registrada la cuenta de correo proporcionada";

    private EmailService emailService;

    public ProveedorServiceImpl(ProveedorProcedureInvokerRepository repository, EmailService emailService) {
        super(repository);
        this.emailService = emailService;
    }

    @Override
    public Respuesta<OpcionDTO> obtenerOpciones(Long ruc) {
        try {
            if(Validador.validarUsuario(ruc)) {
                boolean existeRuc = repository.validarExistencia(ruc.toString());
                if(existeRuc){
                    Optional<OpcionDTO> optOpc= Optional.ofNullable(repository.obtenerOpcionesCambioPassword(ruc.toString()));
                    if(optOpc.isPresent())
                        return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, optOpc.get());
                }
                LOGGER.info(MSG_RUC_NO_REG + ": "+ruc);
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0, MSG_RUC_NO_REG);
            }
            LOGGER.info(MSG_J_RUC_INV);
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_RUC_INV+ruc);
        }catch (Exception ex){
            LOGGER.warn("Method: obtenerOpciones() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
    }

    @Override
    public Respuesta<String> obtenerCorreo(Long ruc){
        try {
            if(Validador.validarUsuario(ruc)){
                Optional<String> optCorreo = Optional.ofNullable(repository.obtenerCorreo(ruc.toString()));
                if(optCorreo.isPresent()){
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, optCorreo.get());
                }
                LOGGER.info(MSG_RS_LEN);
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0, "No cuenta con correo electrónico registrado en el RNP");
            }
            LOGGER.info(MSG_J_RUC_INV);
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_RUC_INV+ ruc);
        } catch (Exception ex){
            LOGGER.warn("Method: obtenerCorreo() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, MSG_EXCEP_PREFIX+ex.getMessage());
        }
    }

    @Override
    public Respuesta<List<CorreoRepDTO>> obtenerListadoCorreoRepresentante(Long ruc) {
        try {
            if(Validador.validarUsuario(ruc)){
                List<CorreoRepDTO> lstCorreos = repository.obtenerListadoCorreoRepresentante(ruc.toString());
                if(!lstCorreos.isEmpty()){
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, lstCorreos);
                }
                LOGGER.info(MSG_MAIL_REP_NO_REG+": "+ruc);
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0, MSG_MAIL_REP_NO_REG);
            }
            LOGGER.info(MSG_J_RUC_INV);
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado es inválido: "+ruc);
        } catch (Exception ex){
            LOGGER.warn("Method: obtenerListadoCorreoRepresentante() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
    }

    @Override
    public Respuesta<String> enviarCorreo(PreCorreoDTO preCorreoDTO) {
        try {
            Long ruc = preCorreoDTO.getRuc();
            String correoDestino = preCorreoDTO.getCorreo();
            if(!Validador.validarUsuario(ruc))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado no es inválido: "+ruc);
            if(!Validador.validarCorreo(correoDestino))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_CORREO_INV+correoDestino);

            Optional<String> optCodVer = Optional.ofNullable(repository.obtenerCodigoVerificacion(ruc.toString(), correoDestino, preCorreoDTO.getIpCliente()));
            if(optCodVer.isPresent()){
                String fecExpYcorreo = preCorreoDTO.getExpiration() +"|"+correoDestino;
                Optional<ContenidoCorreoPOJO> optCorreo = Optional.ofNullable(repository.obtenerContenidoCorreoByTipo(1, ruc.toString(), optCodVer.get(), null, fecExpYcorreo));
                if(optCorreo.isPresent()){
                    ContenidoCorreoPOJO contenidoCorreo = optCorreo.get();
                    Boolean mailEnviado = emailService.enviarCorreoInformativo(contenidoCorreo.getNombreAsunto(), correoDestino,contenidoCorreo.getCuerpo());
                    if(mailEnviado){
                        /*boolean exitoRegistro = repository.registrarCorreoEnviado(ruc.toString(), contenidoCorreo.getAsuntoId(), contenidoCorreo.getCuerpo()).equals("1");
                        if(exitoRegistro)*/
                            return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Un código de verificación ha sido enviado satisfactoriamente a su correo. Revise su bandeja");
                        /*LOGGER.info("El correo ha sido enviado pero no ha sido registrado en BD | Metodo: ProveedorServiceImpl.enviarCorreo(PreCorreoDTO preCorreoDTO)");
                        return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED_BUT_MAIN_REQ_SUCCESS.get(), 1, "Un código de verificación ha sido enviado satisfactoriamente a su correo. Revise su bandeja");*/
                    }
                    LOGGER.info("El servicio de envio de correo no se encuentra disponible en este momento | Metodo: ProveedorServiceImpl.enviarCorreo(PreCorreoDTO preCorreoDTO)");
                    return new Respuesta<>(ResponseCode.EX_MAIL_EXCEPTION.get(), 0, MSG_CORREO_S_FAIL);
                }
            }
            LOGGER.info("El código de verificación no ha podido ser generado");
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El código de verificación no ha podido ser generado");
        } catch (Exception ex){
            LOGGER.warn("Method: enviarCorreo() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, MSG_EXCEP_PREFIX+ex.getMessage());
        }
    }

    @Override
    public Respuesta<List<ForaneaProveedorDTO>> obtenerListadoForaneaProveedor(String foranea) {
        try {
            List<ForaneaProveedorDTO> optLstForanea = repository.obtenerListadoForanea(foranea);
            if(!optLstForanea.isEmpty())
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, optLstForanea);
        }catch (Exception ex){
            LOGGER.warn("Method: obtenerListadoForaneaProveedor() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
        LOGGER.info("Se ha devuelto un listado vacio");
        return new Respuesta<>(ResponseCode.EMPTY_RESPONSE.get(), 0);
    }

    @Override
    public Respuesta<String> validarDatosIdentificacion(DatosIdentificacionDTO dtsIdentificacion, String ipCliente) {
        try{
            if(Validador.validarUsuario(dtsIdentificacion.getRuc())){
                boolean exito = repository.validarDatosIdentificacion(dtsIdentificacion);
                if(!exito){
                    LOGGER.info("Los datos de identificación brindados no han encontrado ninguna coincidencia");
                    return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "Los datos de identificación brindados no han encontrado ninguna coincidencia");
                }
                exito = repository.registrarValPreviaActualizacionCorreo(dtsIdentificacion.getRuc().toString(), ipCliente);
                if(!exito){
                    LOGGER.info("El sp creador del registro de auditoria previo a actualizar el correo ha fallado");
                    return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "El servicio de recuperación de contraseña vía validación de datos no se encuentra disponible");
                }
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Validación satisfactoria");
            }
            LOGGER.info("El ruc proporcionado no es válido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc proporcionado no es válido");
        }catch (Exception ex){
            LOGGER.warn("Method: validarDatosIdentificacion() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, MSG_EXCEP_PREFIX+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> registrarValPreviaActualizacionCorreo(Long ruc, String ipCliente) {
        try{
            boolean exito = repository.registrarValPreviaActualizacionCorreo(String.valueOf(ruc), ipCliente);
            if(!exito){
                LOGGER.info("El sp creador del registro de auditoria previo a actualizar el correo ha fallado");
                return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "El servicio de recuperación de contraseña vía sso de sunat no se encuentra disponible");
            }
            return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Validación satisfactoria");
        }catch (Exception ex){
            LOGGER.warn("Method: validarDatosIdentificacion() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, MSG_EXCEP_PREFIX+ex.getMessage());
        }


    }

    @Override
    public Respuesta<String> actualizarCorreoExtNoDom(Long ruc, String correo) {
        try {
            if(!Validador.validarUsuario(ruc))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado no es inválido: "+ruc);
            if(!Validador.validarCorreo(correo))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_CORREO_INV+correo);

            ProcedureOutputDTO  spOut = repository.actualizarCorreoExtNoDom(ruc.toString(), correo);
            if(spOut.getRespuesta().equals("1"))
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, spOut.getMensaje());
            LOGGER.info(spOut.getMensaje());
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, spOut.getMensaje());
        } catch (Exception ex){
            LOGGER.warn("Method: actualizarCorreoExtNoDom() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, MSG_EXCEP_PREFIX+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> enviarCorreoProvExtNoDomOrRepProvExtNoDom(String correo, int tipoPersona) {
        try {
            if(!Validador.validarCorreo(correo))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_CORREO_INV+correo);
            List<ProExtNoDom> optLst = tipoPersona == 1 ? repository.validarExistenciaCorreoExtNoDom(correo) : repository.validarExistenciaCorreoRepExtNoDom(correo);
            if(!optLst.isEmpty()){
                StringBuilder obsEmpresas = new StringBuilder();
                String suffix="";
                String ruc = optLst.get(0).getCodExtNoDom();
                for(int i=0; i<optLst.size();i++){
                    obsEmpresas.append(suffix);
                    obsEmpresas.append(optLst.get(i).getCodExtNoDom() + "|");
                    obsEmpresas.append(optLst.get(i).getRazSocial() + "|");
                    obsEmpresas.append(optLst.get(i).getNomPais() + "");
                    suffix = "¬";
                }
                Optional<ContenidoCorreoPOJO> optCorreo = Optional.ofNullable(repository.obtenerContenidoCorreoByTipo(4, null, null, obsEmpresas.toString(), null));
                if(optCorreo.isPresent()){
                    ContenidoCorreoPOJO contenidoCorreo = optCorreo.get();
                    Boolean mailEnviado = emailService.enviarCorreoInformativo(contenidoCorreo.getNombreAsunto(), correo, contenidoCorreo.getCuerpo());
                    if(mailEnviado){
                        boolean exitoRegistro = repository.registrarCorreoEnviado(ruc, contenidoCorreo.getAsuntoId(), contenidoCorreo.getCuerpo()).equals("1");
                        if(exitoRegistro)
                            return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, MSG_CONF_CORREO);
                        LOGGER.info("El correo ha sido enviado pero no ha sido registrado en BD | Metodo: ProveedorServiceImpl.enviarCorreoProvExtNoDom(String correo)");
                        return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED_BUT_MAIN_REQ_SUCCESS.get(), 1, MSG_CONF_CORREO);
                    }
                    LOGGER.info(MSG_CORREO_S_FAIL_LOG);
                    return new Respuesta<>(ResponseCode.EX_MAIL_EXCEPTION.get(), 0, MSG_CORREO_S_FAIL);
                }
                LOGGER.info(MSG_CORREO_S_FAIL_LOG);
                return new Respuesta<>(ResponseCode.EMPTY_RESPONSE.get(), 0, MSG_CORREO_S_FAIL);
            }
            LOGGER.info(MSG_MATCHES_NF+": "+correo);
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, MSG_MATCHES_NF);
        }catch (Exception ex){
            LOGGER.warn("Method: enviarCorreoProvExtNoDomOrRepProvExtNoDom() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, MSG_CORREO_S_FAIL);
        }
    }


    @Override
    public Respuesta<List<ProExtNoDom>> obtenerListadoEmpresasExtNoDom(String paisId, Integer tipoPersonaId, String razonSocial) {
        try {
            List<ProExtNoDom> optLst = repository.obtenerListadoEmpresasExtNoDom(paisId, tipoPersonaId, razonSocial);
            if(!optLst.isEmpty())
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, optLst);
            return new Respuesta<>(ResponseCode.EMPTY_RESPONSE.get(), 0);
        }catch (Exception ex){
            LOGGER.warn("Method: obtenerListadoEmpresasExtNoDom() | Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
    }
}
