package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

public interface ContactoProcedureInvokerRepository {

	Mensaje obtenerCorreoUsuario(String ruc);

	Mensaje enviarCorreoRecuperacionPassword(String ruc);

	Mensaje validacionCambioPassword(String hashRuc);
}
