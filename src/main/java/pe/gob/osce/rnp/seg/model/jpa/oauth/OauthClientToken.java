package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "OAUTH_CLIENT_TOKEN")
public class OauthClientToken {

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

}
