package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.Opcion;

public interface Base01ProcedureInvokerRepository {

	 List<Opcion> obtenerOpciones(String ruc);
}
