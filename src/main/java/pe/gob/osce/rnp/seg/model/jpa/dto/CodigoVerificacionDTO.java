package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodigoVerificacionDTO implements Serializable {

    private Long ruc;
    private String correo;
    private String codigo;
    private String ipCliente;
}
