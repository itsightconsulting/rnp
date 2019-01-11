package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class CorreosDTO {

	@Id
	public String correoRepresentante;
	
	public String correoFormateado;
	
	public CorreosDTO() {
		
	}

	public CorreosDTO(String correoRepresentante, String correoFormateado) {
		this.correoRepresentante = correoRepresentante;
		this.correoFormateado = correoFormateado;
	}
}
