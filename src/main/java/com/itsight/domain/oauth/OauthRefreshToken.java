package com.itsight.domain.oauth;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class OauthRefreshToken {

    @Id
    private String tokenId;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(nullable = false)
    private byte[] token;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(nullable = false)
    private byte[] authentication;
}
