package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "OAUTH_CODE")
public class OauthCode {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "AUTHENTICATION", nullable = false, length = Integer.MAX_VALUE)
    private String authentication;

}
