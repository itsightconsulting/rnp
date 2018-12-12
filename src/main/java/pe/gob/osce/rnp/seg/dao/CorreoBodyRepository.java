package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.gob.osce.rnp.seg.model.jpa.CorreoBody;

@Repository
public interface CorreoBodyRepository extends JpaRepository<CorreoBody, Integer>{

}
