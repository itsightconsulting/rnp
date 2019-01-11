package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osce.rnp.seg.model.jpa.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer>{

//    @Transactional
//    @Procedure(name = "sp_obtenercorreousuario")
//    Mensaje insertPais(@Param("C_DES_RUC") String desRuc);
    		
}
