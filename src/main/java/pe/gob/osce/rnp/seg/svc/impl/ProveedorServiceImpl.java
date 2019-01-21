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
            if(Validador.validarUsuario(ruc)) {
                boolean existeRuc = repository.validarExistencia(ruc.toString());
                if(existeRuc){
                    Optional<OpcionDTO> optOpc= Optional.ofNullable(repository.obtenerOpcionesCambioPassword(ruc.toString()));
                    if(optOpc.isPresent())
                        return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, optOpc.get());
                }
                LOGGER.info("Resul set length: 0");
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0, "Resul set length: 0");
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Ruc inválido: "+ruc);
        }catch (Exception ex){
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
                LOGGER.info("Resul set length: 0");
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0, "El ruc no ha coincidido con ninguno de nuestros registros");
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "Ruc inválido: "+ ruc);
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<List<CorreoRepDTO>> obtenerListadoCorreoRepresentante(Long ruc) {
        try {
            if(Validador.validarUsuario(ruc)){
                Optional<List<CorreoRepDTO>> lstCorreos = Optional.ofNullable(repository.obtenerListadoCorreoRepresentante(ruc.toString()));
                if(lstCorreos.isPresent()){
                    return new Respuesta(ResponseCode.EXITO_GENERICA.get(), 1, lstCorreos.get());
                }
                LOGGER.info("Resul set length: 0");
                return new Respuesta(ResponseCode.EMPTY_RESPONSE.get(), 0, "La búsqueda del ruc no ha encontrado coincidencias: "+ruc);
            }
            LOGGER.info("Ruc inválido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado es inválido: "+ruc);
        } catch (Exception ex){
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
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correoDestino);

            Optional<String> optCodVer = Optional.ofNullable(repository.obtenerCodigoVerificacion(ruc.toString(), correoDestino, preCorreoDTO.getIpCliente()));
            if(optCodVer.isPresent()){
                Optional<ContenidoCorreoPOJO> optCorreo = Optional.ofNullable(repository.obtenerContenidoCorreoByTipo(1, ruc.toString(), optCodVer.get()));
                if(optCorreo.isPresent()){
                    ContenidoCorreoPOJO contenidoCorreo = optCorreo.get();
                    Boolean mailEnviado = emailService.enviarCorreoInformativo(contenidoCorreo.getNombreAsunto(), correoDestino,contenidoCorreo.getCuerpo());
                    if(mailEnviado){
                        boolean exitoRegistro = repository.registrarCorreoEnviado(ruc.toString(), contenidoCorreo.getAsuntoId(), contenidoCorreo.getCuerpo()).equals("1");
                        if(exitoRegistro)
                            return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Un código de verificación ha sido enviado satisfactoriamente a su correo. Revise su bandeja");
                        LOGGER.info("El correo ha sido enviado pero no ha sido registrado en BD | Metodo: ProveedorServiceImpl.enviarCorreo(PreCorreoDTO preCorreoDTO)");
                        return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED_BUT_MAIN_REQ_SUCCESS.get(), 1, "Un código de verificación ha sido enviado satisfactoriamente a su correo. Revise su bandeja");
                    }
                    LOGGER.info("El servicio de envio de correo no se encuentra disponible en este momento | Metodo: ProveedorServiceImpl.enviarCorreo(PreCorreoDTO preCorreoDTO)");
                    return new Respuesta<>(ResponseCode.EX_MAIL_EXCEPTION.get(), 0, "El servicio de envio de correo no se encuentra disponible en este momento. Intentarlo nuevamente más tarde");
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
            LOGGER.info("Excepcion: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
        LOGGER.info("Se ha devuelto un listado vacio");
        return new Respuesta<>(ResponseCode.EMPTY_RESPONSE.get(), 0);
    }

    @Override
    public Respuesta<String> validarDatosIdentificacion(DatosIdentificacionDTO dtsIdentificacion) {
        try{
            if(Validador.validarUsuario(dtsIdentificacion.getRuc())){
                boolean exito = repository.validarDatosIdentificacion(dtsIdentificacion);
                if(exito)
                    return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Validación satisfactoria");
                LOGGER.info("Los datos de identificación brindados no han encontrado ninguna coincidencia");
                return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "Los datos de identificación brindados no han encontrado ninguna coincidencia");
            }
            LOGGER.info("El ruc proporcionado no es válido");
            return new Respuesta(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc proporcionado no es válido");
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> actualizarCorreoExtNoDom(Long ruc, String correo) {
        try {
            if(!Validador.validarUsuario(ruc))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El ruc ingresado no es inválido: "+ruc);
            if(!Validador.validarCorreo(correo))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correo);

            ProcedureOutputDTO  spOut = repository.actualizarCorreoExtNoDom(ruc.toString(), correo);
            if(spOut.getRespuesta().equals("1"))
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, spOut.getMensaje());
            LOGGER.info(spOut.getMensaje());
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, spOut.getMensaje());
        } catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<String> enviarCorreoProvExtNoDom(String correo) {
        try {
            if(!Validador.validarCorreo(correo))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correo);
            Optional<List<ProExtNoDom>> optLst = Optional.ofNullable(repository.validarExistenciaCorreoExtNoDom(correo));
            if(optLst.isPresent()){
                /*repository.obtenerContenidoCorreoByTipo(2, null, null);
                repository.obtenerContenidoCorreoByTipo(3, null, null);*/

                emailService.enviarCorreoInformativo("Recuperación de Usuario RNP", correo, contenidoCorreoRecuperacionUsuario(optLst.get()));
                boolean exito = repository.registrarCorreoEnviado(optLst.get().get(0).getCodExtNoDom(), 1, correo).equals("1");//TEMP: optLst.get().get(0).getCodExtNoDom()
                if(exito)
                    return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Un correo acaba de ser enviado a la dirección proporcionada dentro del cúal podrá ver el nombre de su usuario");
                LOGGER.info("El correo ha sido enviado pero no ha sido registrado en BD");
                return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "El correo ha sido enviado pero no ha sido registrado en BD");
            }
            LOGGER.info("No se ha encontrado ninguna coincidencia con el correo ingresado");
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "No se ha encontrado ninguna coincidencia con el correo ingresado");
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    private String contenidoCorreoRecuperacionUsuario(List<ProExtNoDom> lst){
        StringBuilder sb = new StringBuilder(1000);
        if(lst.size() == 1){
            sb.append("<p> Su nombre de usuario RNP es: <br/>");
            sb.append("<b>"+lst.get(0).getCodExtNoDom()+"</b></p>");
        }else{
            sb.append("<p> El correo brindado tiene asociado varias empresas por lo cual le brindamos ");
            sb.append("la siguiente lista con sus respectivos nombres de usuario: </p>");
            sb.append("<table>");
            sb.append("<thead>");
            sb.append("<tr><th>Usuario</th><th>Razón Social</th></tr>");
            sb.append("</thead>");
            sb.append("<tbody>");
            lst.forEach(x->sb.append("<tr><td>"+x.getCodExtNoDom()+"</td><td>"+x.getRazSocial()+"</td>"));
            sb.append("</tbody>");
            sb.append("</table>");
        }
        return sb.toString();
    }

    @Override
    public Respuesta<String> enviarCorreoRepProvExtNoDom(String correo) {
        try {
            if(!Validador.validarCorreo(correo))
                return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "El correo presenta un formato inválido: "+correo);
            Optional<List<ProExtNoDom>> optLst = Optional.ofNullable(repository.validarExistenciaCorreoRepExtNoDom(correo));
            if(optLst.isPresent()){
                emailService.enviarCorreoInformativo("Recuperación de Usuario RNP", correo, contenidoCorreoRecuperacionUsuario(optLst.get()));
                boolean exito = repository.registrarCorreoEnviado(optLst.get().get(0).getCodExtNoDom(), 1,correo).equals("1");//TEMP: optLst.get().get(0).getCodExtNoDom()
                if(exito)
                    return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, "Un correo acaba de ser enviado a la dirección proporcionada dentro del cúal podrá ver el nombre de su usuario");
                LOGGER.info("El correo ha sido enviado pero no ha sido registrado en BD");
                return new Respuesta<>(ResponseCode.EX_SP_VALIDATION_FAILED.get(), 0, "El correo ha sido enviado pero no ha sido registrado en BD");
            }
            LOGGER.info("No se ha encontrado ninguna coincidencia con el correo ingresado");
            return new Respuesta<>(ResponseCode.EX_VALIDATION_FAILED.get(), 0, "No se ha encontrado ninguna coincidencia con el correo ingresado");
        }catch (Exception ex){
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0, "Excepción: "+ex.getMessage());
        }
    }

    @Override
    public Respuesta<List<ProExtNoDom>> obtenerListadoEmpresasExtNoDom(String paisId, Integer tipoPersonaId, String razonSocial) {
        try {
            Optional<List<ProExtNoDom>> optLst = Optional.ofNullable(repository.obtenerListadoEmpresasExtNoDom(paisId, tipoPersonaId, razonSocial));
            if(optLst.isPresent())
                return new Respuesta<>(ResponseCode.EXITO_GENERICA.get(), 1, optLst.get());
            return new Respuesta<>(ResponseCode.EMPTY_RESPONSE.get(), 0);
        }catch (Exception ex){
            LOGGER.info("Excepción: "+ex.getMessage());
            return new Respuesta<>(ResponseCode.EX_GENERIC.get(), 0);
        }
    }
}
