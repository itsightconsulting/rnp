package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ListadoEmpresaExtDTO {

	@Id
	public String codExtNoDom;
	
	public String razonSocial;
	
	public ListadoEmpresaExtDTO() {
		
	}

	public ListadoEmpresaExtDTO(String codExtNoDom, String razonSocial) {
		this.codExtNoDom = codExtNoDom;
		this.razonSocial = razonSocial;
	}
}
