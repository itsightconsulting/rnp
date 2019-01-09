package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;

public interface Base01ProcedureInvokerRepository {

	 List<OpcionDTO> obtenerOpciones(String ruc);
}
