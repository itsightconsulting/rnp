package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.pojo.ContenidoCorreoPOJO;

public interface ProveedorProcedureInvoker {

    Boolean validarExistencia(String ruc);

    OpcionDTO obtenerOpcionesCambioPassword(String ruc);

    String obtenerCorreo(String ruc);

    String obtenerCodigoVerificacion(String ruc, String correo, String ipCliente);

    ContenidoCorreoPOJO obtenerContenidoCorreoByTipo(int tipo, String ruc, String codVerificacion);

    String registrarCorreoEnviado(String ruc, Integer idAsunto, String cuerpoCorreo);

}
