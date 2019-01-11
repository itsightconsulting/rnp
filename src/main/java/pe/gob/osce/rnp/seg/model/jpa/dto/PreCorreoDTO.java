package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PreCorreoDTO implements Serializable {

    @NotNull
    private Long ruc;
    @NotNull
    private String correo;
    @NotNull
    private String ipCliente;
}
