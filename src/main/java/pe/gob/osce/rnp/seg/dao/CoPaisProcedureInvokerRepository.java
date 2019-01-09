package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.dto.ListadoCorreosDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ListadoDatosIdentifiacionDTO;

public interface CoPaisProcedureInvokerRepository {

	List<ListadoDatosIdentifiacionDTO> obtenerDatosIdentificacion(String indTipo);

}
