package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;

public interface ClaveAccesoProcedureInvokerRepository {

	ProcedureOutputDTO validaProveedorRnp(String nombre);
	ProcedureOutputDTO guardarClave(String ruc, String clave);
	ProcedureOutputDTO validaUsuario(String ruc, String clave);
	ProcedureOutputDTO validaNuevaClave(String clave1, String clave2);

}
