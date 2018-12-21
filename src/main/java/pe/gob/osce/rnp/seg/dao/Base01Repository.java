package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osce.rnp.seg.model.jpa.Base01;

@Repository

public interface Base01Repository extends JpaRepository<Base01, Integer>{

    @Transactional
    @Procedure(name = "sp_obteneropciones")
    void obtenerOpciones(@Param("RUC") String ruc);

}
