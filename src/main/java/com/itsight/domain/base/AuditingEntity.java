package com.itsight.domain.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itsight.json.JsonDateSimpleDeserializer;
import com.itsight.json.JsonDateSimpleSerializer;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class AuditingEntity {

    @Column(nullable = false, updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date creationDate;

    @Column
    private String modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    private Date modificationDate;

    @Column(nullable = false)
    private boolean flagActive;

    @Column(nullable = false)
    private boolean flagElimination;

    @Column(nullable = true)
    private Long rowVersion;

    public AuditingEntity(){}

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean isFlagActive() {
        return flagActive;
    }

    public void setFlagActive(boolean flagActive) {
        this.flagActive = flagActive;
    }

    public boolean isFlagElimination() {
        return flagElimination;
    }

    public void setFlagElimination(boolean flagElimination) {
        this.flagElimination = flagElimination;
    }

    public Long getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }
}
