package pe.gob.osce.rnp.seg.model.jpa;

<<<<<<< HEAD
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
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
