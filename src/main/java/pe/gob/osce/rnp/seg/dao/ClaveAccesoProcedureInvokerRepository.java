package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

public interface ClaveAccesoProcedureInvokerRepository {

	public Mensaje validaProveedorRnp(String nombre);
	public Mensaje guardarClave(String ruc, String clave);
	public Mensaje validaUsuario(String ruc, String clave);
	public Mensaje validaNuevaClave(String clave1, String clave2);

}
