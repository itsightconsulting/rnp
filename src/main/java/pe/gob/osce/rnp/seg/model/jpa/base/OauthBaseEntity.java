package pe.gob.osce.rnp.seg.model.jpa.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class OauthBaseEntity {

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

    public OauthBaseEntity(){
        /*
         *
         */
    }

    public void setTokenId(String tokenId){
        this.tokenId = tokenId;
    }

    public String getTokenId(){
        return this.tokenId;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public void setAuthenticationId(String authenticationId){
        this.authenticationId = authenticationId;
    }

    public String getAuthenticationId(){
        return this.authenticationId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setClientId(String clientId){
        this.clientId = clientId;
    }

    public String getClientId(){
        return this.clientId;
    }
}
