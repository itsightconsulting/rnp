package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "OAUTH_CLIENT_DETAILS")
public class OauthClientDetails {

    @Id
    @Column(name = "CLIENT_ID")
    private String clientId;
    @Column(name = "RESOURCE_IDS", nullable = false)
    private String resourceIds;
    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;
    @Column(name = "SCOPE", nullable = false)
    private String scope;
    @Column(name = "AUTHORIZED_GRANT_TYPES", nullable = false)
    private String authorizedGrantTypes;
    @Column(name = "WEB_SERVER_REDIRECT_URI", nullable = false)
    private String webServerRedirectUri;
    @Column(name = "AUTHORITIES", nullable = false)
    private String authorities;
    @Column(name = "ACCESS_TOKEN_VALIDITY", nullable = false)
    private int accessTokenValidity;
    @Column(name = "REFRESH_TOKEN_VALIDITY", nullable = false)
    private int refreshTokenValidity;
    //Siempre tiene que ser un Map<Key, Value>, para el caso un JSON string
    @Column(name = "ADDITIONAL_INFORMATION", nullable = false)
    private String additionalInformation;
    @Column(name = "AUTOAPPROVE", nullable = false)
    private String autoapprove;

}
