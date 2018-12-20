package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.rnp.seg.model.jpa.Contacto;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer>{

//    @Transactional
//    @Procedure(name = "sp_obtenercorreousuario")
//    Mensaje insertPais(@Param("C_DES_RUC") String desRuc);
    		
}
