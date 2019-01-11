package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osce.rnp.seg.model.jpa.ClaveAcceso;

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
