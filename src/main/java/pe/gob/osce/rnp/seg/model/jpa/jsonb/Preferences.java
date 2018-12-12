package pe.gob.osce.rnp.seg.model.jpa.jsonb;

import java.io.Serializable;
import java.util.List;

public class Preferences implements Serializable {

    private List<Residue> residues;

    public List<Residue> getResidues() {
        return residues;
    }

    public void setResidues(List<Residue> residues) {
        this.residues = residues;
    }

    public Preferences(){}
}
