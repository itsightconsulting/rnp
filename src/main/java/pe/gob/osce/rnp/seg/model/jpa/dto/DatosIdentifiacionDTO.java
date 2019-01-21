package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class DatosIdentifiacionDTO {

	@Id
	public String iden;
	
	public String valor;
	
	public DatosIdentifiacionDTO() {
		
	}

	public DatosIdentifiacionDTO(String iden, String valor) {
		this.iden = iden;
		this.valor = valor;
	}
}
