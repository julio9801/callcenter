/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itaca.callcenter.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author israel
 */
public class CargaValidatorBase implements CargaValidator
{
	private static final Logger logger = LogManager.getLogger(CargaValidatorBase.class);
	
    public boolean validate(String valueToValidate) throws UtilitiesException
    
    {
    	//logger.info("Ejecutando validate()");
        if(valueToValidate == null || valueToValidate.equals(""))
        {
            throw new UtilitiesException("valor nulo o cadena vacia");
        }
        return true;
    }
}
