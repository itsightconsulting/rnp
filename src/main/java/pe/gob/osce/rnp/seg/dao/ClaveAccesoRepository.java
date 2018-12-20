package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.rnp.seg.model.jpa.ClaveAcceso;
import pe.gob.osce.rnp.seg.model.jpa.Mensaje;

@Repository
public interface ClaveAccesoRepository extends JpaRepository<ClaveAcceso, Integer>{

//    @Transactional
//    @Procedure(name = "sp_validausuario")
//    Mensaje validaUsuario(@Param("ruc") String ruc, @Param("clave") String clave);
//
//    @Transactional
//    @Procedure(name = "sp_guardarclave")
//    Mensaje guardarClave(@Param("C_DES_RUC") String desRuc, @Param("clave") String clave);

}
