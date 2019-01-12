package pe.gob.osce.rnp.seg.svc;

import pe.gob.osce.rnp.seg.model.jpa.dto.*;

import java.util.List;

public interface ProveedorService {

    Respuesta<OpcionDTO> obtenerOpciones(Long ruc);

    Respuesta<String> obtenerCorreo(Long ruc);

    Respuesta<List<CorreoRepDTO>> obtenerListadoCorreoRepresentante(Long ruc);

    Respuesta<String> enviarCorreo(PreCorreoDTO preCorreoDTO);

    Respuesta<List<ForaneaProveedorDTO>> obtenerListadoForaneaProveedor(String foranea);

    Respuesta<String> validarDatosIdentificacion(DatosIdentificacionDTO dtsIdentificacion);

    Respuesta<String> actualizarCorreoExtNoDom(Long ruc, String correo);

    Respuesta<String> validarCorreoExtNoDom(String correo);
}
