package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ListadoCorreosDTO {

	@Id
	public String correoRepresentante;
	
	public String correoFormateado;
	
	public ListadoCorreosDTO() {
		
	}

	public ListadoCorreosDTO(String correoRepresentante, String correoFormateado) {
		this.correoRepresentante = correoRepresentante;
		this.correoFormateado = correoFormateado;
	}
}
