package pe.gob.osce.rnp.seg.dao;

<<<<<<< HEAD
import pe.gob.osce.rnp.seg.model.jpa.dto.CodigoVerificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;

public interface PrmClaConfiguracionProcedureInvokerRepository {

	ProcedureOutputDTO registrarCodVerificacion(CodigoVerificacionDTO codigoVerificacionDto);
=======
import pe.gob.osce.rnp.seg.model.jpa.Mensaje2;

public interface PrmClaConfiguracionProcedureInvokerRepository {

	public Mensaje2 registrarCodVerificacion(String ruc, String correo, String codUid, String desIp);

//	public Mensaje ejecutarSPDemo(String nombre);
//	public String obtenerOpciones(String ruc);

//	public Mensaje registrarCodVerificacion(String ruc, String correo, String codUid, String desIp);
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
}
