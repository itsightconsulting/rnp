package pe.gob.osce.rnp.seg.model.jpa.dto;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import pe.gob.osce.rnp.seg.json.JsonDateSimpleDeserializer;
import pe.gob.osce.rnp.seg.json.JsonDateSimpleSerializer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosIdentificacionDTO implements Serializable {

    @NotNull
    @Min(1999999999)
    public Long ruc;

    @NotNull
    @Min(0)
    @Max(999)
    public Integer paisId;

    @NotNull
    @Length(min = 1, max = 2)
    public String tipoDocuId;

    @NotNull
    @Size(min = 1)
    public String desDocu;

    @NotNull
    @Min(1)
    public Integer zonaRegistralId;

    @NotNull
    @Size(min = 1)
    public String nroPartida;

    @NotNull
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    @JsonDeserialize(using = JsonDateSimpleDeserializer.class)
    public Date fecIngreso;

    @NotNull
    @Min(0)
    @Max(9)
    public Integer tipoCondicionId;
}
=======
import java.util.Date;

import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import pe.gob.osce.rnp.seg.json.JsonDateSimpleSerializer;

@Data
public class DatosIdentificacionDTO {

	@Id
	public String ruc;
	
	public String idPais;
	
	public String idTipoDocu;
	
	public String desDocu;
	
	public Integer idZonaRegistral;
	
	public String nroPartida;
	
	@JsonSerialize(using = JsonDateSimpleSerializer.class)
	public Date fecIngreso;
	
	public String idTipoCondicion;
	
	public DatosIdentificacionDTO() {
		
	}

	public DatosIdentificacionDTO(String ruc, String idPais, String idTipoDocu, String desDocu, Integer idZonaRegistral,
			String nroPartida, Date fecIngreso, String idTipoCondicion) {
		this.ruc = ruc;
		this.idTipoDocu = idTipoDocu;
		this.desDocu = desDocu;
		this.idZonaRegistral = idZonaRegistral;
		this.nroPartida = nroPartida;
		this.fecIngreso = fecIngreso;
		this.idTipoCondicion = idTipoCondicion;
		this.idPais = idPais;
	}
}
>>>>>>> e7a74bebe9504292fbea8fd39fcc7b562f6e0bdc
