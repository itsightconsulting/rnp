package pe.gob.osce.rnp.seg.model.jpa.dto;

import java.util.Date;

import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import pe.gob.osce.rnp.seg.json.JsonDateSimpleSerializer;

@Data
public class MensajeCuerpoDTO {

	@Id
	public Integer indTipo;
	
	public String ruc;
	
	@JsonSerialize(using = JsonDateSimpleSerializer.class)
	public Date fecCambio;
	
	public String codVerificacion;
	
	public String desObservacion1;
	
	public String desObservacion2;
	
	public String desObservacion3;
	
	public String desObservacion4;
	
	public MensajeCuerpoDTO() {
		
	}

	public MensajeCuerpoDTO(Integer indTipo, String ruc, Date fecCambio, String codVerificacion, String desObservacion1,
			String desObservacion2, String desObservacion3, String desObservacion4) {
		this.indTipo = indTipo;
		this.ruc = ruc;
		this.fecCambio = fecCambio;
		this.codVerificacion = codVerificacion;
		this.desObservacion1 = desObservacion1;
		this.desObservacion2 = desObservacion2;
		this.desObservacion3 = desObservacion3;
		this.desObservacion4 = desObservacion4;
	}
}
