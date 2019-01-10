package pe.gob.osce.rnp.seg.model.jpa.dto;

import javax.persistence.Id;

import org.apache.axis2.databinding.types.soapencoding.DateTime;

import lombok.Data;

@Data
public class MensajeCuerpoDTO {

	/*@N_IND_TIPO INT,
	@C_DES_RUC VARCHAR(11),
	@D_FEC_CAMBIO DATETIME,
	@C_COD_VERIFIC VARCHAR(6),
	@C_DES_OBSERV1 VARCHAR(1000),
	@C_DES_OBSERV2 VARCHAR(1000),
	@C_DES_OBSERV3 VARCHAR(1000),
	@C_DES_OBSERV4 VARCHAR(1000)	*/
	@Id
	public Integer indTipo;
	
	public String ruc;
	
	public DateTime fecCambio;
	
	public String codVerificacion;
	
	public String desObservacion1;
	
	public String desObservacion2;
	
	public String desObservacion3;
	
	public String desObservacion4;
	
	public MensajeCuerpoDTO() {
		
	}

	public MensajeCuerpoDTO(Integer indTipo, String ruc, DateTime fecCambio, String codVerificacion, String desObservacion1,
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
