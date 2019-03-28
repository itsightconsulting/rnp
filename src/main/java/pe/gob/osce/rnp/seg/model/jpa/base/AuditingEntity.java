package pe.gob.osce.rnp.seg.model.jpa.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.gob.osce.rnp.seg.json.JsonDateSimpleDeserializer;
import pe.gob.osce.rnp.seg.json.JsonDateSimpleSerializer;

@MappedSuperclass
public class AuditingEntity {

    @Column(name = "CREADO_POR", nullable = false, updatable = false)
    private String creadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION", nullable = false, updatable = false)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaCreacion;

    @Column(name = "MODIFICADO_POR")
    private String modificadoPor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_MODIFICACION")
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date fechaModificacion;

    @Column(name = "FLAG_ACTIVO", nullable = false)
    private boolean flagActivo;

    @Column(name = "FLAG_ELIMINADO", nullable = false)
    private boolean flagEliminado;

    @Column(name = "ROW_VERSION")
    private Long rowVersion;

    public AuditingEntity(){
        /*
         *
         */
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public boolean isFlagActivo() {
        return flagActivo;
    }

    public void setFlagActivo(boolean flagActivo) {
        this.flagActivo = flagActivo;
    }

    public boolean isFlagEliminado() {
        return flagEliminado;
    }

    public void setFlagEliminado(boolean flagEliminado) {
        this.flagEliminado = flagEliminado;
    }

    public Long getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }
}
