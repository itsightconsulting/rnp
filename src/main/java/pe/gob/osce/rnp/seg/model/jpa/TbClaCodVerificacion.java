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
public class TbClaCodVerificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_NUM_CODVERIFICACION")
	public Integer idVerificacion;

	@Column(name = "C_DES_RUC")
	public String desRuc;

	@Column(name = "C_DES_CORREO")
	public String desCorreo;

	@Column(name = "C_DES_CODVERIFICACION")
	public String desCodVerificacion;

	@Column(name = "N_IND_ULTIMO")
	public Integer indUltimo;

	@Column(name = "N_IND_ACTIVO")
	public Integer indActivo;

	@Column(name = "N_IND_USADO")
	public Integer indUsado;

	@Column(name = "D_FEC_INIVALIDEZ")
	public DateTime fecIniValidez;

	@Column(name = "D_FEC_FINVALIDEZ")
	public DateTime fecFinVallidez;

	@Column(name = "D_FEC_REGISTRO")
	public DateTime fecRegistro;

	@Column(name = "D_FEC_USADO")
	public DateTime fecUsado;

	@Column(name = "C_COD_USUREGISTRO")
	public String codUsuRegistro;

	@Column(name = "C_DES_IPUSUREGISTRO")
	public String desIpUsuRegistro;

	@Column(name = "C_DES_OBSERVACION")
	public String desObservacion;

	public TbClaCodVerificacion(){}

}

