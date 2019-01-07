package pe.gob.osce.rnp.seg.model.jpa;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.gob.osce.rnp.seg.model.jpa.base.AuditingEntity;


@Entity
@Table(name = "Parametro")
@Data
@EqualsAndHashCode(callSuper = false)
public class Parametro extends AuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ParametroId")
    private int id;

    @Column(nullable = false, updatable = false)
    private String parametro;

    @Size(min = 1)
    @Column(nullable = false)
    private String valor;

    public Parametro(){}

    public Parametro(String parametro, String valor) {
        this.parametro = parametro;
        this.valor = valor;
    }
}