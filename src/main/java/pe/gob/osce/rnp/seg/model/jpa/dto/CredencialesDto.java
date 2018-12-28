package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CredencialesDto implements Serializable {

    private String ruc;
    private String clave;
}
