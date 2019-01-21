package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class CorreosProvExtDTO {

	@Id
	public String codExtNoDom;
	
	public String correo;
	
	public CorreosProvExtDTO() {
		
	}

	public CorreosProvExtDTO(String codExtNoDom, String correo) {
		this.codExtNoDom = codExtNoDom;
		this.correo = correo;
	}
}
