package com.itsight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer>{

}
