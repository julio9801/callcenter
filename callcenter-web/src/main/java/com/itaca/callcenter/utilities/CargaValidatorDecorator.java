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
public abstract class CargaValidatorDecorator implements CargaValidator
{
	private static final Logger logger = LogManager.getLogger(CargaValidatorDecorator.class);
	
    private CargaValidator m_validator;

    public CargaValidatorDecorator(CargaValidator validator)
    {
        m_validator = validator;
    }
    
    @Override
    public boolean validate(String valueToValidate) throws UtilitiesException
    {
    	logger.info("Ejecutando validate()");
        return this.m_validator.validate(valueToValidate);
    }
}
