package pe.gob.osce.rnp.seg.model.jpa.pojo;

import lombok.Data;

@Data
public class ContenidoCorreoPOJO {

    private int asuntoId;

    private String nombreAsunto;

    private String cuerpo;

    public ContenidoCorreoPOJO(int asuntoId, String nombreAsunto, String cuerpo) {
        this.asuntoId = asuntoId;
        this.nombreAsunto = nombreAsunto;
        this.cuerpo = cuerpo;
    }
}
