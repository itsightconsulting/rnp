package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProExtNoDom implements Serializable {

    private String codExtNoDom;
    private String razSocial;
    private String nomPais;

    public ProExtNoDom(String codExtNoDom, String razSocial) {
        this.codExtNoDom = codExtNoDom;
        this.razSocial = razSocial;
    }

    public ProExtNoDom(String codExtNoDom, String razSocial, String nomPais) {
        this.codExtNoDom = codExtNoDom;
        this.razSocial = razSocial;
        this.nomPais = nomPais;
    }
}
