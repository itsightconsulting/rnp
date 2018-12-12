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
public class TipoContacto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_CONTACTO")
	private Integer idTipoContacto;
	
	@Column(name = "DE_TIPO_CONTACTO")
	private String deTipoContacto;
	
    @JsonBackReference
    @OneToMany(mappedBy = "tipoContacto", fetch = FetchType.LAZY)
    private List<Contacto> lstContacto;
    
    @Column(name = "ID_TIPO_ASIENTO")
	private int idTipoAsiento;
}
