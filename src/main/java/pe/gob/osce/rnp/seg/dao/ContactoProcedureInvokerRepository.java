package pe.gob.osce.rnp.seg.dao;

<<<<<<< HEAD
import pe.gob.osce.rnp.seg.model.jpa.dto.ProcedureOutputDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
=======
import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.dto.CorreosDTO;
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc

public interface ContactoProcedureInvokerRepository {

	ProcedureOutputDTO obtenerCorreoUsuario(String ruc);

<<<<<<< HEAD
	ProcedureOutputDTO enviarCorreoRecuperacionPassword(String ruc);

	ProcedureOutputDTO validacionCambioPassword(String hashRuc);

    Respuesta<String> obtenerCorreo(String ruc);
=======
	List<CorreosDTO> obtenerCorreoRepresentante(String ruc);

>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
}
