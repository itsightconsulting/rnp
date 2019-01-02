package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

public interface ContactoProcedureInvokerRepository {

	Mensaje obtenerCorreoUsuario(String ruc);

}
