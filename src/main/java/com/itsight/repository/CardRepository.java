package com.itsight.repository;

import com.itsight.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findAll();

    Card findById(int id);

    @Transactional//Importante para el correcto funcionamiento
    @Procedure(name = "update_card")
    Integer updateWithSp(@Param("id") Integer id, @Param("description") String description);

    @Procedure(name = "sp_suma_demo")
    Integer getSumaDemo(@Param("numOne") Integer numOne, @Param("numTwo") Integer numTwo);

}