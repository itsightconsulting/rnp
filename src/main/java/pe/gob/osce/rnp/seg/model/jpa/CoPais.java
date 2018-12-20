package pe.gob.osce.rnp.seg.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "sp_insert_pais",
        procedureName = "sp_insertarPais",
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.IN, name="descripcion", type=String.class),
          @StoredProcedureParameter(mode=ParameterMode.OUT, name="mensaje", type=String.class)
    }),
    @NamedStoredProcedureQuery(
            name = "sp_update_pais",
            procedureName = "[sp_updatePais]",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="codPais", type=Integer.class),
              @StoredProcedureParameter(mode=ParameterMode.IN, name="descripcion", type=String.class)

              //              @StoredProcedureParameter(mode=ParameterMode.OUT, name="outputParam", type=String.class)
    }),
    @NamedStoredProcedureQuery(
            name = "sp_eliminar_Pais",
            procedureName = "sp_eliminarPais",
            parameters = {
              @StoredProcedureParameter(mode=ParameterMode.IN, name="id", type=Integer.class)
//              @StoredProcedureParameter(mode=ParameterMode.OUT, name="outputParam", type=String.class)
    }),
})
@Data
//@Table(name = "CoPais")
public class CoPais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CodPais")
	private Integer codPais;
	
	@Column(name = "descripcion")
	private String descripcion;
}
