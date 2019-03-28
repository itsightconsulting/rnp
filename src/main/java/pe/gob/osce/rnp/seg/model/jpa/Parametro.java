package pe.gob.osce.rnp.seg.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.gob.osce.rnp.seg.model.jpa.base.AuditingEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "PARAMETRO")
@Data
@EqualsAndHashCode(callSuper = false)
public class Parametro extends AuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAMETRO_ID")
    private int id;

    @Column(name = "PARAMETRO", nullable = false, updatable = false)
    private String clave;

    @Size(min = 1)
    @Column(name = "VALOR", nullable = false)
    private String valor;

    public Parametro(){}

    public Parametro(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }
}
