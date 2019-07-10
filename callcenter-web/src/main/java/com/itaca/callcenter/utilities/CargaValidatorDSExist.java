/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itaca.callcenter.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itaca.callcenter.constant.PatternsConstants;
import com.itaca.callcenter.dao.GenericDAO;

/**
 * Este validador verifica la existencia de un valor en una fuente de datos.
 * Normalmente se verifica su existencia para obtener el índice del valor
 * 
 */
public class CargaValidatorDSExist extends CargaValidatorDecorator {
	
	private static final Logger logger = LogManager.getLogger(CargaValidatorDSExist.class);
	
	private GenericDAO m_dao;
	private String m_field;

	public CargaValidatorDSExist(CargaValidator validator) {
		super(validator);
	}

	public void setDAO(GenericDAO dao) {
		m_dao = dao;
		logger.info("daoC[" + m_dao + "]  daoP[" + dao + "]");
	}

	public void setField(String field) {
		m_field = field;
		logger.info("fieldC[" + m_field + "]  fieldP[" + field + "]");
	}

  
	public boolean validate(String valueToValidate) throws UtilitiesException {
		logger.info(" Validate dao[" + m_dao + "]");
		boolean valid = false;
              
		if(valueToValidate.isEmpty()==false){
			try{
				// verifica si el valor se encuentra en la fuente de datos
				Object o ;
				if(m_field.equals("id")){
					o = m_dao.findByField(m_field, Long.valueOf(valueToValidate));
				}else{
					o = m_dao.findByField(m_field, valueToValidate);
				}
				logger.info("TST o [" + o + "]");
				
					
//					if (o instanceof com.itaca..domain.entities.IcClientes) {
//						valid = true;
//					}else{
						throw new  UtilitiesException("El valor NO se encuentra en la fuente de datos");
					//}
					
				}catch(UtilitiesException e){
					throw e;
				} catch(NumberFormatException e){
					throw new  UtilitiesException(PatternsConstants.ERROR_NUMERICO);
				}

		}
		return valid;
	}

}
