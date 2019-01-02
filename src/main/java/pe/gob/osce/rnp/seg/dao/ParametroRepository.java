package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.osce.rnp.seg.model.jpa.Parametro;

import java.util.List;


@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {

    List<Parametro> findAll();
}