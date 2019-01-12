package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CorreoRepDTO implements Serializable {

    public String correoRepresentante;

    public String correoFormateado;

    public CorreoRepDTO(String correoRepresentante, String correoFormateado) {
        this.correoRepresentante = correoRepresentante;
        this.correoFormateado = correoFormateado;
    }
}
