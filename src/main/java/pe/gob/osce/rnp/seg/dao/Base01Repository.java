package pe.gob.osce.rnp.seg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.osce.rnp.seg.model.jpa.Base01;

@Repository
public interface Base01Repository extends JpaRepository<Base01, Integer>{

}
