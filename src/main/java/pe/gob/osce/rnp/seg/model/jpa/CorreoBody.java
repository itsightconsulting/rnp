package pe.gob.osce.rnp.seg.model.jpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CorreoBody {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_ORIGEN_CORREO")
	private Integer ID_TIPO_ORIGEN_CORREO; 
	
	@Column(name = "FLAG_LEIDO")
	private String flagLeido; 
	
	@Column(name = "RUC")
	private String ruc; 
	
	@Column(name = "RECEPTOR_CORREO")
	private String receptorCorreo; 
	
	@Column(name = "CAPITULO")
	private int capitulo; 
	
	@Column(name = "ASUNTO")
	private String asunto; 
	
	@Column(name = "MENSAJE")
	private String mensaje; 
	
	@Column(name = "EMISOR")
	private String emisor; 
	
	@Column(name = "EMISOR_CORREO")
	private String emisorCorreo; 
	
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.DATE)
	private Date fRegistro;
	
	@Column(name = "F_RECEPCION")
	@Temporal(TemporalType.DATE)
	private Date fRecepcion;
	
	@Column(name = "NU_ANO_TRAMITE")
	private String nuAnoTramite; 
	
	@Column(name = "NU_TRAMITE")
	private int nuTramite;
	
	@Column(name = "ID_REGION")
	private String idRegion; 
	
	@Column(name = "ES_EXTERNO")
	private String esExterno;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_ASUNTO")
	private TipoAsuntoBandeja tipoAsuntoBandeja;
	 
}
