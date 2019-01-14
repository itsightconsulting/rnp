package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.model.jpa.dto.Respuesta;

public interface SeguridadService {

    Respuesta<String> validarCodVerificacion(Long ruc, Long codVer);

    Respuesta<String> actualizarClave(Long ruc, String clave, String correoDestino);

    Respuesta<String> validarIngreso(Long ruc, String clave);
}
