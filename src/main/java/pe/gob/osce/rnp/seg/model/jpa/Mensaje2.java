package pe.gob.osce.rnp.seg.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mensaje2 {

	public String respuesta;
	
	public String mensaje;
	
	public String desCodVerifiacion;
	
	public Mensaje2() {}
	
}
