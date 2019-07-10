package com.itaca.callcenter.utilities;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

public interface FactoryValidator {

	public FactoryValidator getInstance();
	
	public List<CargaValidator> createValidator(int index, EntityManager entityManager);
	
	public HashMap<Integer, String> getmMapValidators();
}
	