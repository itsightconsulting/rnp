package com.itsight.repository;

import com.itsight.domain.oauth.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {

}
