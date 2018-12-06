package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.Base01a;

@Repository
public interface Base01aRepository extends JpaRepository<Base01a, Integer>{

}
