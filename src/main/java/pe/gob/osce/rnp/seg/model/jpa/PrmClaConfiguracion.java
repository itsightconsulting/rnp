package pe.gob.osce.rnp.seg.model.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "sp_registrarcodverificacion",
        procedureName = "spregistrarcodverificacion",
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.IN, name="C_DES_RUC", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="C_DES_CORREO", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="C_COD_UID", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="C_DES_IP", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.IN, name="C_DES_CODVERIFICACION", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.OUT, name="RESPUESTA", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.OUT, name="MENSAJE", type=String.class)
    })
})
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
