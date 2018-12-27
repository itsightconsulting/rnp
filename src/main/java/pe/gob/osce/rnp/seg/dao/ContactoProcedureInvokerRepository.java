package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

public interface ContactoProcedureInvokerRepository {

//	public Mensaje ejecutarSPDemo(String nombre);
	public Mensaje obtenerCorreoUsuario(String ruc);

//	public Mensaje registrarCodVerificacion(String ruc, String correo, String codUid, String desIp);
}
