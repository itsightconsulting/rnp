package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcedureOutputDTO implements Serializable {

    private String respuesta;

    private String mensaje;

    public ProcedureOutputDTO(){}

    public ProcedureOutputDTO(String respuesta, String mensaje) {
        this.respuesta = respuesta;
        this.mensaje = mensaje;
    }
}
