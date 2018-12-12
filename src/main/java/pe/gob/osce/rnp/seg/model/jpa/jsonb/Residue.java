package pe.gob.osce.rnp.seg.model.jpa.jsonb;

import java.io.Serializable;

public class Residue implements Serializable {

    private double amount;

    private String description;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Residue(){}

    public Residue(double amount, String description){
        this.amount = amount;
        this.description = description;
    }
}
