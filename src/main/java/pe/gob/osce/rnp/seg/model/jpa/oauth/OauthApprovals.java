package pe.gob.osce.rnp.seg.model.jpa.oauth;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OAUTH_APPROVALS")
@Data
public class OauthApprovals {

    @Id
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;
    @Column(name = "SCOPE", nullable = false)
    private String scope;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "EXPIRES_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;
    @Column(name = "LAST_MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedAt;
}
