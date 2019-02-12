package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "OAUTH_ACCESS_TOKEN")
@Data
public class OauthAccessToken {

    @Id
    @Column(name = "TOKEN_ID")
    private String tokenId;
    @Column(name = "TOKEN", nullable = false, length = Integer.MAX_VALUE)
    private String token;
    @Column(name = "AUTHENTICATION_ID", nullable = false)
    private String authenticationId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;
    @Column(name = "AUTHENTICATION", nullable = false, length = Integer.MAX_VALUE)
    private String authentication;
    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;
}
