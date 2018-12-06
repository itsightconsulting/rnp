package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.ClaveAcceso;

@Repository
public interface ClaveAccesoRepository extends JpaRepository<ClaveAcceso, Integer>{

}
