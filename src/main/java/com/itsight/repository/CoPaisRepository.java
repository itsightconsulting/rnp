package com.itsight.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsight.domain.CoPais;

@Repository
public interface CoPaisRepository extends JpaRepository<CoPais, Integer>{

	
	List<CoPais> findAll();
	
//	CoPais findByid(int id);
//    @Procedure(name = "sp_insertarPais")
//    void procedureName(@Param("Descripcion") String inputParam1);
}
