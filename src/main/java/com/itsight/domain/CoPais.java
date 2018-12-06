package com.itsight.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CoPais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CodPais")
	private Integer codPais;
	
	@Column(name = "descripcion")
	private String Descripcion;
}
