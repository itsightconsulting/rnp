package pe.gob.osce.rnp.seg.model.jpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Contacto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RNP_CONTACTO")
	private Integer idRnpContacto;
	
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_EXP")
	private Base01 base01;
    
    @Column(name = "DE_CONTACTO")
	private String deContacto;
    
    @Column(name = "DE_ADICIONAL")
	private String deAdicional;
    
    @Column(name = "LT_RUC")
	private String ltRuc;
    
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CONTACTO")
	private TipoContacto tipoContacto;
}