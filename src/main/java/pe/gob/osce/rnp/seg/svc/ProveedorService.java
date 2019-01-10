package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;

public interface ProveedorService {

    Respuesta<OpcionDTO> obtenerOpciones(String ruc);
}
