package pe.gob.osce.rnp.seg.model.jpa.oauth;

import lombok.EqualsAndHashCode;
import pe.gob.osce.rnp.seg.model.jpa.base.OauthBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OAUTH_CLIENT_TOKEN")
@EqualsAndHashCode(callSuper = false)
public class OauthClientToken extends OauthBaseEntity {


}
