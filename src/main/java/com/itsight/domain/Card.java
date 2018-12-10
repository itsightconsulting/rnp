package com.itsight.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "update_card",
                procedureName = "update_card",
                parameters = {
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="id", type=Integer.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="description", type=String.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="retrn_id", type=Integer.class)
                }),
})
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CardId")
    private int id;

    private String description;

    public Card(String description) { this.description = description; }

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