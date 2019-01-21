package pe.gob.osce.rnp.seg.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mensaje {

	public String mensaje;
	
	public String respuesta;
	
	public Mensaje() {}
	
}
