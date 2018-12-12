package pe.gob.osce.rnp.seg.model.jpa.base;

import java.util.Date;

public interface BaseEntity {

    public String getCreador();

    public void setCreador(String creador);

    public Date getFechaCreacion();

    public void setFechaCreacion(Date fechaCreacion);

    public String getActualizador();

    public void setActualizador(String actualizador);

    public Date getFechaActualizacion();

    public void setFechaActualizacion(Date fechaActualizacion);
}
