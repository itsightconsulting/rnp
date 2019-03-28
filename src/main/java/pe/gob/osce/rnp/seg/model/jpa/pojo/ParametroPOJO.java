package pe.gob.osce.rnp.seg.model.jpa.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class ParametroPOJO implements Serializable {

    private int id;

    @Size(min = 1)
    @Column(name = "VALOR", nullable = false)
    private String valor;

    public ParametroPOJO(){
        /*
         *
         */
    }

    public ParametroPOJO(String valor) {
        this.valor = valor;
    }
}
