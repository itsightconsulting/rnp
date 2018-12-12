package pe.gob.osce.rnp.seg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osce.rnp.seg.model.jpa.Parameter;


@Repository
public interface ParameterRepository extends JpaRepository<Parameter,Integer> {

    List<Parameter> findAll();
}