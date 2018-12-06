package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.TipoAsuntoBandejaDet;

@Repository
public interface TipoAsuntoBandejaDetRepository extends JpaRepository<TipoAsuntoBandejaDet, Integer>{

}
