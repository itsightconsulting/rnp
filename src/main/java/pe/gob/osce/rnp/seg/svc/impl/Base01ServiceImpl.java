package pe.gob.osce.rnp.seg.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.osce.rnp.seg.dao.Base01Repository;
import pe.gob.osce.rnp.seg.model.jpa.Base01;
import pe.gob.osce.rnp.seg.svc.Base01Service;

import java.util.List;

@Service
public class Base01ServiceImpl implements Base01Service{

	@Autowired
	private Base01Repository base01Repository;
	
	@Override
	public Base01 save(Base01 entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Base01 update(Base01 entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Base01 findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Base01 findOneWithFT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Base01> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
  
}
