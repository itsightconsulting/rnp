package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.TipoAsuntoBandeja;

@Repository
public interface TipoAsuntoBandejaRepository extends JpaRepository<TipoAsuntoBandeja, Integer>{

}
