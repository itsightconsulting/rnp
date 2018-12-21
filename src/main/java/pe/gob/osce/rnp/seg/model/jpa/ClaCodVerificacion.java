package pe.gob.osce.rnp.seg.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.axis2.databinding.types.soapencoding.DateTime;

import lombok.Data;

@Entity
@Data
public class ClaCodVerificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NUM_CODVERIFICACION")
	private Integer CodVerificacion;
	
	@Column(name = "C_DES_RUC")
	private String desRuc;
	
	@Column(name = "C_DES_CORREO")
	private String desCorreo;

	@Column(name = "C_DES_CODVERIFICACION")
	private String desCodVerificacion;
	
	@Column(name = "N_IND_ULTIMO")
	private Integer indUltimo;
	
	@Column(name = "N_IND_ACTIVO")
	private Integer indActivo;
	
	@Column(name = "N_IND_USADO")
	private Integer indUsado;
	
	@Column(name = "D_FEC_INIVALIDEZ")
	private DateTime fecIniValidez;
	
	@Column(name = "D_FEC_FINVALIDEZ")
	private DateTime fecFinValidez;
	
	@Column(name = "D_FEC_REGISTRO")
	private DateTime fecRegistro;
	
	@Column(name = "D_FEC_USADO")
	private DateTime fecUsado;
	
	@Column(name = "C_COD_USUREGISTRO")
	private String codUsuRegistro;
	
	@Column(name = "C_DES_IPUSUREGISTRO")
	private String desIpUsuRegistro;
	
	@Column(name = "C_DES_OBSERVACION")
	private String desObservacion;
	
}
