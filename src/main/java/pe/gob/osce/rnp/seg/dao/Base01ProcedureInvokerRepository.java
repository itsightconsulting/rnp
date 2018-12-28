package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.Opcion;

public interface Base01ProcedureInvokerRepository {

	 public List<Opcion> obtenerOpciones(String ruc);
}
