package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class EmpresaExtDTO {

	@Id
	public String codExtNoDom;
	
	public String razonSocial;
	
	public EmpresaExtDTO() {
		
	}

	public EmpresaExtDTO(String codExtNoDom, String razonSocial) {
		this.codExtNoDom = codExtNoDom;
		this.razonSocial = razonSocial;
	}
}
