package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class OauthClientDetails {

    @Id
    private String clientId;
    @Column(nullable = false)
    private String resourceIds;
    @Column(nullable = false)
    private String clientSecret;
    @Column(nullable = false)
    private String scope;
    @Column(nullable = false)
    private String authorizedGrantTypes;
    @Column(nullable = false)
    private String webServerRedirectUri;
    @Column(nullable = false)
    private String authorities;
    @Column(nullable = false)
    private int accessTokenValidity;
    @Column(nullable = false)
    private int refreshTokenValidity;
    //Siempre tiene que ser un Map<Key, Value>, para el caso un JSON string
    @Column(nullable = false)
    private String additionalInformation;
    @Column(nullable = false)
    private String autoapprove;
}
