package pe.gob.osce.rnp.seg.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mensaje {

	public String respuesta;
	
	public String mensaje;

	public Mensaje() {}
	
}
