package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

@Data
public class ForaneaProveedorDTO {

    private Integer iden;

    private String valor;

    public ForaneaProveedorDTO(Integer iden, String valor) {
        this.iden = iden;
        this.valor = valor;
    }
}
