package pe.gob.osce.rnp.seg.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import pe.gob.osce.rnp.seg.model.jpa.base.AuditingEntity;

@Entity
public class Parametro extends AuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ParameterId")
    private int id;

    private String name;

    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Parametro(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Parametro(int id) { this.id = id; }

    public Parametro(){}
}