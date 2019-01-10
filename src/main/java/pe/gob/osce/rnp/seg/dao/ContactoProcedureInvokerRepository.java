package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;

public interface ContactoProcedureInvokerRepository {

	ProcedureOutputDTO obtenerCorreoUsuario(String ruc);

	ProcedureOutputDTO enviarCorreoRecuperacionPassword(String ruc);

	ProcedureOutputDTO validacionCambioPassword(String hashRuc);

    Respuesta<String> obtenerCorreo(String ruc);
}
