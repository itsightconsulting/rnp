package pe.gob.osce.rnp.seg.model.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PrmClaConfiguracion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COD_CONFIGURACION")
	private Integer codConfiguracion;
	
	@Column(name = "C_DES_CONFIGURACION")
	private String desConfiguracion;
	
	@Column(name = "C_DES_VALOR")
	private String desValor;
	
	@Column(name = "N_IND_ACTIVO")
	private Integer indActivo;
	
	@Column(name = "D_FEC_REGISTRO")
	private Date fecRegistro;
	
	@Column(name = "C_COD_USUREGISTRO")
	private String codUsuregistro;
	
	@Column(name = "C_DES_IPUSUREGISTRO")
	private String desIpUsuRegistro;
	
}
