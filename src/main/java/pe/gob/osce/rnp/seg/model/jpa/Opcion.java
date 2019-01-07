package pe.gob.osce.rnp.seg.model.jpa;

import lombok.Data;

@Data
public class Opcion {

	public String opcion1;
	
	public String opcion2;
	
	public String opcion3;
	
	public String opcion4;
	
	public Opcion() {
		
	}

	public Opcion(String opcion1, String opcion2, String opcion3, String opcion4) {
		this.opcion1 = opcion1;
		this.opcion2 = opcion2;
		this.opcion3 = opcion3;
		this.opcion4 = opcion4;
	}
}
