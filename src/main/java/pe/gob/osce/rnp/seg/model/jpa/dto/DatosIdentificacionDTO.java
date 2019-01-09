package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import org.apache.axis2.databinding.types.soapencoding.DateTime;

import lombok.Data;

@Data
public class DatosIdentificacionDTO {

	@Id
	public String ruc;
	
	public String idPais;
	
	public String idTipoDocu;
	
	public String desDocu;
	
	public Integer idZonaRegistral;
	
	public String nroPartida;
	
	public DateTime fecIngreso;
	
	public String idTipoCondicion;
	
	public DatosIdentificacionDTO() {
		
	}

	public DatosIdentificacionDTO(String ruc, String idPais, String idTipoDocu, String desDocu, Integer idZonaRegistral,
			String nroPartida, DateTime fecIngreso, String idTipoCondicion) {
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
