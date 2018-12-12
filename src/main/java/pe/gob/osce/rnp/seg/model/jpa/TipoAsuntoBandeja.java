package pe.gob.osce.rnp.seg.model.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class TipoAsuntoBandeja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_ASUNTO")
	private Integer idTipoAsunto;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ACTIVO")
	private String activo;
	
	@Column(name = "OBSERVACIONES")
	private String observaciones;
	
    @JsonBackReference
    @OneToMany(mappedBy = "tipoAsuntoBandeja", fetch = FetchType.LAZY)
    private List<TipoAsuntoBandejaDet> lstTipoAsuntoBandejaDet;
	
    @JsonBackReference
    @OneToMany(mappedBy = "tipoAsuntoBandeja", fetch = FetchType.LAZY)
    private List<CorreoBody> lstCorreoBody;
	
    
	public TipoAsuntoBandeja() {
		
	}
	
}