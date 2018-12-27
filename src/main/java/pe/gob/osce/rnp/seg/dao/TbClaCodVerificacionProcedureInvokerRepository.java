package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

public interface TbClaCodVerificacionProcedureInvokerRepository {

    public Mensaje validarCodVerificacion(String ruc, String desCodVerificacion);

//	public Mensaje ejecutarSPDemo(String nombre);
//	public String obtenerOpciones(String ruc);

//	public Mensaje registrarCodVerificacion(String ruc, String correo, String codUid, String desIp);
}
