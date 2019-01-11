package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje2;

public interface PrmClaConfiguracionProcedureInvokerRepository {

	public Mensaje2 registrarCodVerificacion(String ruc, String correo, String codUid, String desIp);

//	public Mensaje ejecutarSPDemo(String nombre);
//	public String obtenerOpciones(String ruc);

//	public Mensaje registrarCodVerificacion(String ruc, String correo, String codUid, String desIp);
}
