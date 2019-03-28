package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.gob.osce.rnp.seg.model.jpa.base.OauthBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "OAUTH_ACCESS_TOKEN")
@Data
@EqualsAndHashCode(callSuper = false)
public class OauthAccessToken extends OauthBaseEntity {

    @Column(name = "AUTHENTICATION", nullable = false, length = Integer.MAX_VALUE)
    private String authentication;
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;
}
