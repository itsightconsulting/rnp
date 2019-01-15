package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class OauthAccessToken {

    @Id
    private String tokenId;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private String authenticationId;
    @Column()
    private String userName;
    @Column(nullable = false)
    private String clientId;
    @Column(nullable = false)
    private String authentication;
    @Column
    private String refreshToken;
}
