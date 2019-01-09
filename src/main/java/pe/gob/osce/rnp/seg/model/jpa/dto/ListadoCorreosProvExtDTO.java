package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ListadoCorreosProvExtDTO {

	@Id
	public String codExtNoDom;
	
	public String correo;
	
	public ListadoCorreosProvExtDTO() {
		
	}

	public ListadoCorreosProvExtDTO(String codExtNoDom, String correo) {
		this.codExtNoDom = codExtNoDom;
		this.correo = correo;
	}
}
