package com.itsight.domain.oauth;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class OauthAccessToken {

    @Id
    private String tokenId;
    @Column(nullable = false)
    private byte[] token;
    @Column(nullable = false)
    private String authenticationId;
    @Column()
    private String userName;
    @Column(nullable = false)
    private String clientId;
    @Column(nullable = false)
    private byte[] authentication;
    @Column
    private String refreshToken;
}
