package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.model.jpa.dto.OpcionDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.PreCorreoDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;

public interface ProveedorService {

    Respuesta<OpcionDTO> obtenerOpciones(String ruc);

    Respuesta<String> obtenerCorreo(String ruc);

    Respuesta<String> enviarCorreo(PreCorreoDTO preCorreoDTO);

}
