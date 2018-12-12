package pe.gob.osce.rnp.seg.model.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
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