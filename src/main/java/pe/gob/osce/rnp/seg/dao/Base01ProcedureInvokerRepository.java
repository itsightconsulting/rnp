package pe.gob.osce.rnp.seg.dao;

import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;

public interface Base01ProcedureInvokerRepository {

	Respuesta obtenerOpciones(String ruc);
}
