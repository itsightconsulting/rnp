package com.itsight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class TipoAsuntoBandejaDet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_REGISTRO")
	private Integer idTipoRegistro;
	
	@Column(name = "ID_TIPO_POSTOR")
	private Integer idTipoPostor;
	
	@Column(name = "ID_REPRES_LEGAL")
	private Integer idRepresLegal;
	
	@Column(name = "ID_TIPO_ASUNTO_DET")
	private Integer idTipoAsuntoDet;
	
	@Column(name = "ID_VERSION")
	private Integer idVersion;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ESTADO")
	private Integer estado;
	
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_ASUNTO")
	private TipoAsuntoBandeja tipoAsuntoBandeja;	
	
}
