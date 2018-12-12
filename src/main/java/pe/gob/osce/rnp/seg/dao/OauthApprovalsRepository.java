package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.rnp.seg.model.jpa.oauth.OauthApprovals;

@Repository
@Transactional
public interface OauthApprovalsRepository extends JpaRepository<OauthApprovals, String> {
}
