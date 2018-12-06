package com.itsight.domain;

import com.itsight.domain.base.AuditingEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Parameter extends AuditingEntity implements Serializable {

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

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Parameter(int id) { this.id = id; }

    public Parameter(){}
}