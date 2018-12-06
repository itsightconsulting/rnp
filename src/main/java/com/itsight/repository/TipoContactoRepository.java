package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.TipoContacto;

@Repository
public interface TipoContactoRepository extends JpaRepository<TipoContacto, Integer>{

}
