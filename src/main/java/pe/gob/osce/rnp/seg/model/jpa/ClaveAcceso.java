package pe.gob.osce.rnp.seg.model.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity

//@NamedStoredProcedureQueries({
//    @NamedStoredProcedureQuery(
//        name = "sp_validausuario",
//        procedureName = "spvalidausuario",
//        parameters = {
//          @StoredProcedureParameter(mode=ParameterMode.IN, name="ruc", type=String.class),
//          @StoredProcedureParameter(mode=ParameterMode.IN, name="clave", type=String.class),
//          @StoredProcedureParameter(mode=ParameterMode.OUT, name="resultado", type=String.class),
//          @StoredProcedureParameter(mode=ParameterMode.OUT, name="mensaje", type=String.class)
//    }),
//    @NamedStoredProcedureQuery(
//            name = "sp_guardarclave",
//            procedureName = "spguardarclave",
//            parameters = {
//              @StoredProcedureParameter(mode=ParameterMode.IN, name="C_DES_RUC", type=String.class),
//              @StoredProcedureParameter(mode=ParameterMode.IN, name="clave", type=String.class),
//              @StoredProcedureParameter(mode=ParameterMode.OUT, name="resultado", type=String.class),
//              @StoredProcedureParameter(mode=ParameterMode.OUT, name="mensaje", type=String.class)
//        }),
//})    

@Data
public class ClaveAcceso {

	@Id
	@Column(name = "RUC")
	private String ruc;
	
	@Column(name = "CLAVE")
	private String clave;
	
	@Column(name = "ESTADO")
	private int estado;
	
	@Column(name = "FECHA")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "PROCESADO")
	private int procesado;
}