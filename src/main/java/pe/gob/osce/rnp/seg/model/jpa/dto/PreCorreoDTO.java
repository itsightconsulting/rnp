package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PreCorreoDTO implements Serializable {

    @NotNull
    @Size(min = 11, max = 11)
    private Long ruc;
    @NotNull
    @Size(min = 1)
    private String correo;
    @NotNull
    @Size(min = 1)
    private String ipCliente;

}
