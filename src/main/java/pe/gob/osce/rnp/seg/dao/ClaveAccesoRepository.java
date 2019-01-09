package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.osce.rnp.seg.model.jpa.ClaveAcceso;

@Repository
public interface ClaveAccesoRepository extends JpaRepository<ClaveAcceso, Integer>{

}
