package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import pe.gob.osce.rnp.seg.model.jpa.dto.DatosIdentifiacionDTO;

public interface CoPaisProcedureInvokerRepository {

	List<DatosIdentifiacionDTO> obtenerDatosIdentificacion(String indTipo);

}
