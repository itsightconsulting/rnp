package com.itsight.repository;

import com.itsight.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findAll();

    Card findById(int id);
}