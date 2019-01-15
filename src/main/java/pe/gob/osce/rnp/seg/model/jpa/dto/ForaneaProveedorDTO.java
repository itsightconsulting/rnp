package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

@Data
public class ForaneaProveedorDTO {

    private String iden;

    private String valor;

    public ForaneaProveedorDTO(String iden, String valor) {
        this.iden = iden;
        this.valor = valor;
    }
}
