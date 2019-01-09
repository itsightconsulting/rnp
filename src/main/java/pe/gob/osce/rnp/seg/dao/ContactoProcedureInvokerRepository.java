package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;

public interface ContactoProcedureInvokerRepository {

	ProcedureOutputDTO obtenerCorreoUsuario(String ruc);

	ProcedureOutputDTO enviarCorreoRecuperacionPassword(String ruc);

	ProcedureOutputDTO validacionCambioPassword(String hashRuc);
}
