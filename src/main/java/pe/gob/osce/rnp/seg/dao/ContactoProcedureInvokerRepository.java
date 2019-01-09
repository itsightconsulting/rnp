package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.Mensaje;
import pe.gob.osce.rnp.seg.model.jpa.dto.ListadoCorreosDTO;

public interface ContactoProcedureInvokerRepository {

	Mensaje obtenerCorreoUsuario(String ruc);

	List<ListadoCorreosDTO> obtenerCorreoRepresentante(String ruc);

}
