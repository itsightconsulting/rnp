package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osce.rnp.seg.model.jpa.CoPais;

@Repository
public interface CoPaisRepository extends JpaRepository<CoPais, Integer>{

	
	List<CoPais> findAll();
	
//    @Transactional
//    @Procedure(name = "sp_insert_pais")
//    StoredProcedureQuery insertPais(@Param("descripcion") String descripcion);
//
//    @Transactional
//    @Procedure(name = "sp_update_pais")
//    void updatePais(@Param("codPais") Integer codPais, @Param("descripcion") String descripcion);
//
//    @Transactional
//    @Procedure(name = "sp_eliminar_Pais")
//    void eliminar(@Param("id") Integer codPais);

    
//	CoPais findByid(int id);
//    @Procedure(name = "sp_insertarPais")
//    void procedureName(@Param("Descripcion") String inputParam1);
}
