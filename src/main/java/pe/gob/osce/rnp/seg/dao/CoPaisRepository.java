package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.rnp.seg.model.jpa.CoPais;

@Repository
public interface CoPaisRepository extends JpaRepository<CoPais, Integer>{

	
	List<CoPais> findAll();
	
    @Transactional
    @Procedure(name = "sp_insert_pais")
    void insertPais(@Param("descripcion") String descripcion);

    @Transactional
    @Procedure(name = "sp_update_pais")
    void updatePais(@Param("codPais") Integer codPais, @Param("descripcion") String descripcion);

    
//	CoPais findByid(int id);
//    @Procedure(name = "sp_insertarPais")
//    void procedureName(@Param("Descripcion") String inputParam1);
}
