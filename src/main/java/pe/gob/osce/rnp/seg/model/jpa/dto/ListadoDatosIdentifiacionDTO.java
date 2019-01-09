package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ListadoDatosIdentifiacionDTO {

	@Id
	public String iden;
	
	public String valor;
	
	public ListadoDatosIdentifiacionDTO() {
		
	}

	public ListadoDatosIdentifiacionDTO(String iden, String valor) {
		this.iden = iden;
		this.valor = valor;
	}
}
