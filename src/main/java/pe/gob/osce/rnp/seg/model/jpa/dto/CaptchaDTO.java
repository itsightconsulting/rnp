package pe.gob.osce.rnp.seg.model.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CaptchaDTO implements Serializable {

    private String answer;

    private String b64image;
}
