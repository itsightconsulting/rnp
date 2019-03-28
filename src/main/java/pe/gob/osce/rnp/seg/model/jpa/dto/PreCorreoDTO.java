package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class PreCorreoDTO implements Serializable {

    @NotNull
    @Min(1999999999)
    private Long ruc;
    @NotNull
    @Size(min = 1)
    private String correo;
    @Null
    private String ipCliente;

    private String expiration;

}
