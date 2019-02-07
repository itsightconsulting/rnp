package pe.gob.osce.rnp.seg.model.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
	private Date fecIniValidez;
	
	@Column(name = "D_FEC_FINVALIDEZ")
	private Date fecFinValidez;
	
	@Column(name = "D_FEC_REGISTRO")
	private Date fecRegistro;
	
	@Column(name = "D_FEC_USADO")
	private Date fecUsado;
	
	@Column(name = "C_COD_USUREGISTRO")
	private String codUsuRegistro;
	
	@Column(name = "C_DES_IPUSUREGISTRO")
	private String desIpUsuRegistro;
	
	@Column(name = "C_DES_OBSERVACION")
	private String desObservacion;
	
}
