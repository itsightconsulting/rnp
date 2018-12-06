package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.Base01;

@Repository
public interface Base01Repository extends JpaRepository<Base01, Integer>{

}
