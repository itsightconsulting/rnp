package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;
import pe.gob.osce.rnp.seg.model.jpa.dto.UpdClaveDto;

public interface SeguridadService {

    Respuesta<String> validarCodVerificacion(Long ruc, Long codVer);

    Respuesta<String> actualizarClave(UpdClaveDto updClaveDto);

    Respuesta<String> validarIngreso(Long ruc, String clave);
}
