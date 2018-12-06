package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.CoPais;

@Repository
public interface CoPaisRepository extends JpaRepository<CoPais, Integer>{

}
