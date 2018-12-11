package com.itsight.service;

import com.itsight.domain.CoPais;
import com.itsight.generic.BaseService;

public interface CoPaisService extends BaseService<CoPais>{

	public String registrar(String string);
	public String eliminar(int id);
	public CoPais buscar(int id);
}
