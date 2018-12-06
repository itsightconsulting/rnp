package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.CorreoBody;

@Repository
public interface CorreoBodyRepository extends JpaRepository<CorreoBody, Integer>{

}
