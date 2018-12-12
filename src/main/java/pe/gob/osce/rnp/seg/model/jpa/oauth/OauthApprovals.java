package pe.gob.osce.rnp.seg.model.jpa.oauth;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class OauthApprovals {

    @Id
    private String userId;
    @Column(nullable = false)
    private String clientId;
    @Column(nullable = false)
    private String scope;
    @Column(nullable = false)
    private String status;
    @Column(name = "expiresAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedAt;
}
