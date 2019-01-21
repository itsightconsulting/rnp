package pe.gob.osce.rnp.seg.dao;

<<<<<<< HEAD
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;

public interface Base01ProcedureInvokerRepository {

	Respuesta obtenerOpciones(String ruc);
=======
import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.dto.CorreosProvExtDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.DatosIdentificacionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.EmpresaExtDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;

public interface Base01ProcedureInvokerRepository {

	List<OpcionDTO> obtenerOpciones(String ruc);

	public Mensaje registrarCorreo(String ruc, String correo, String correoConfirmacion);

	public Mensaje registrarMensaje(String ruc, String idAsunto, String desMensaje);

	public List<CorreosProvExtDTO> validaCorreoExtNoDom(String correo);

	public List<CorreosProvExtDTO> validaCorreoRepExtNoDom(String correo);

	public Mensaje validarDatosIdentificacion(DatosIdentificacionDTO datosIdentificacionDto);

	public List<EmpresaExtDTO> validaEmpresaExtNoDom(String codPais, Integer indPnp, String razonSocial);

//	public String obtenerMensaje(MensajeCuerpoDTO mensajeCuerpoDto);
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
}
