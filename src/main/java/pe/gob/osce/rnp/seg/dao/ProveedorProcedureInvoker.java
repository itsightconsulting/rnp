package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.*;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;

import java.util.List;

public interface ProveedorProcedureInvoker {

    Boolean validarExistencia(String ruc);

    OpcionDTO obtenerOpcionesCambioPassword(String ruc);

    String obtenerCorreo(String ruc);

    String obtenerCodigoVerificacion(String ruc, String correo, String ipCliente);

    ContenidoCorreoPOJO obtenerContenidoCorreoByTipo(int tipo, String ruc, String codVerificacion);

    String registrarCorreoEnviado(String ruc, Integer idAsunto, String cuerpoCorreo);

    List<ForaneaProveedorDTO> obtenerListadoForanea(String tipo);

    Boolean validarDatosIdentificacion(DatosIdentificacionDTO dtsIdentificacion);

    List<ProExtNoDom> validarExistenciaCorreoExtNoDom(String correo);

    List<ProExtNoDom> validarExistenciaCorreoRepExtNoDom(String correo);

    ProcedureOutputDTO actualizarCorreoExtNoDom(String ruc, String correo);

    List<CorreoRepDTO> obtenerListadoCorreoRepresentante(String ruc);

    List<ProExtNoDom> obtenerListadoEmpresasExtNoDom(String paisId, Integer tipoPersonaId, String razonSocial);
}
