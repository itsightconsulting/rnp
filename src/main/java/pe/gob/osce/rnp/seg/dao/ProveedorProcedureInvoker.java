package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;

public interface ProveedorProcedureInvoker {

    Boolean validarExistencia(String ruc);

    OpcionDTO obtenerOpcionesCambioPassword(String ruc);

    String obtenerCorreo(String correo);
}
