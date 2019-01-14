package pe.gob.osce.rnp.seg.model.jpa.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
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
    @Size(min = 11, max = 11)
    public Long ruc;

    @NotNull
    @Size(max = 3)
    public String paisId;

    @NotNull
    @Min(0)
    public Integer tipoDocuId;

    @NotNull
    @Size(min = 1)
    public String desDocu;

    @NotNull
    @Min(0)
    public Integer zonaRegistralId;

    @NotNull
    @Size(min = 1)
    public String nroPartida;

    @NotNull
    @JsonSerialize(using = JsonDateSimpleSerializer.class)
    public Date fecIngreso;

    @NotNull
    @Min(0)
    public Integer tipoCondicionId;
}