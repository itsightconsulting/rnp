package pe.gob.osce.rnp.seg.model.jpa;

import java.io.Serializable;

public class SecurityPrivelege implements Serializable {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}