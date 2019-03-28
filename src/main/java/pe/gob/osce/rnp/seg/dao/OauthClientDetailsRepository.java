package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.osce.rnp.seg.model.jpa.oauth.OauthClientDetails;

@Repository
@Transactional
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {

    @Modifying
    @Query("UPDATE OauthClientDetails O SET O.clientSecret = ?2 WHERE O.clientId = ?1")
    void updateClientSecret(String clientId, String clientSecret);
}
