package pe.gob.osce.rnp.seg.model.jpa.dto;

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
