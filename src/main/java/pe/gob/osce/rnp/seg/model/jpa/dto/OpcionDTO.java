package pe.gob.osce.rnp.seg.model.jpa.dto;

<<<<<<< HEAD:src/main/java/pe/gob/osce/rnp/seg/model/jpa/dto/OpcionDTO.java
=======
import javax.persistence.Id;

>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc:src/main/java/pe/gob/osce/rnp/seg/model/jpa/dto/OpcionDTO.java
import lombok.Data;

@Data
public class OpcionDTO {

	public String opcion1;
	public String opcion2;
	public String opcion3;
	
	public String opcion4;
	
	public OpcionDTO() {
		
	}

	public OpcionDTO(String opcion1, String opcion2, String opcion3, String opcion4) {
		this.opcion1 = opcion1;
		this.opcion2 = opcion2;
		this.opcion3 = opcion3;
		this.opcion4 = opcion4;
	}
}
