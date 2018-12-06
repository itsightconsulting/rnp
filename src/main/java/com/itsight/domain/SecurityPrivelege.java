package com.itsight.domain;

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