package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.Data;

@Data
public class OpcionDTO {

	private String opcion1;
	private String opcion2;
	private String opcion3;
	private String opcion4;
	
	public OpcionDTO() {
		
	}

	public OpcionDTO(String opcion1, String opcion2, String opcion3, String opcion4) {
		this.opcion1 = opcion1;
		this.opcion2 = opcion2;
		this.opcion3 = opcion3;
		this.opcion4 = opcion4;
	}
}
