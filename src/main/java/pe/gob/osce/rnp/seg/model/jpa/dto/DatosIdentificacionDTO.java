package pe.gob.osce.rnp.seg.model.jpa.dto;

import java.util.Date;

import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import pe.gob.osce.rnp.seg.json.JsonDateSimpleSerializer;

@Data
public class DatosIdentificacionDTO {

	@Id
	public String ruc;
	
	public String idPais;
	
	public String idTipoDocu;
	
	public String desDocu;
	
	public Integer idZonaRegistral;
	
	public String nroPartida;
	
	@JsonSerialize(using = JsonDateSimpleSerializer.class)
	public Date fecIngreso;
	
	public String idTipoCondicion;
	
	public DatosIdentificacionDTO() {
		
	}

	public DatosIdentificacionDTO(String ruc, String idPais, String idTipoDocu, String desDocu, Integer idZonaRegistral,
			String nroPartida, Date fecIngreso, String idTipoCondicion) {
		this.ruc = ruc;
		this.idTipoDocu = idTipoDocu;
		this.desDocu = desDocu;
		this.idZonaRegistral = idZonaRegistral;
		this.nroPartida = nroPartida;
		this.fecIngreso = fecIngreso;
		this.idTipoCondicion = idTipoCondicion;
		this.idPais = idPais;
	}
}
