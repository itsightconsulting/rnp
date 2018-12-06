package com.itsight.domain;

import com.itsight.domain.base.AuditingEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Card extends AuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CardId")
    private int id;

    private String description;

    public Card(int id) { this.id = id; }

    public Card() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}