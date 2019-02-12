package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "OAUTH_REFRESH_TOKEN")
public class OauthRefreshToken {

    @Id
    @Column(name = "TOKEN_ID")
    private String tokenId;
    @Column(name = "TOKEN", nullable = false, length = Integer.MAX_VALUE)
    private byte[] token;
    @Column(name = "AUTHENTICATION", nullable = false, length = Integer.MAX_VALUE)
    private byte[] authentication;
}
