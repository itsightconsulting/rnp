package pe.gob.osce.rnp.seg.model.jpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CoPais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CodPais")
	private Integer codPais;
	
	@Column(name = "descripcion")
	private String descripcion;
}
