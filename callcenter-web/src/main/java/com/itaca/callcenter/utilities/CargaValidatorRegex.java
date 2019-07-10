package com.itaca.callcenter.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author israel
 */
public class CargaValidatorRegex extends CargaValidatorDecorator
{
	private static final Logger logger = LogManager.getLogger(CargaValidatorRegex.class);
	
    private String m_regexPattern;
    private Pattern m_pattern;
    private String error;
            
    public CargaValidatorRegex(CargaValidator validator)
    {
        super(validator);
        m_regexPattern = ".";
        m_pattern = Pattern.compile(m_regexPattern);
    }
    
    public CargaValidatorRegex(CargaValidator validator, String regex)
    {
        this(validator);
        setRegex(regex);
        m_pattern = Pattern.compile(m_regexPattern);
    }
    
    public void setRegex(String regex, String mensaje)
    {
        if(regex == null || regex.equals(""))
        {
            m_regexPattern = ".";
        }
        else
        {
            m_regexPattern = regex;
            error = mensaje;
        }
    }
    
    public void setRegex(String regex)
    {
        if(regex == null || regex.equals(""))
        {
            m_regexPattern = ".";
        }
        else
        {
            m_regexPattern = regex;
        }
    }
    
    
    public boolean validate(String valueToValidate) throws UtilitiesException
    {
    	logger.info("Ejecutando validate() - REGEX");
    	m_pattern = Pattern.compile(m_regexPattern);
        Matcher matcher = m_pattern.matcher(valueToValidate);
        boolean result = matcher.matches();
        logger.info("REGEX: " +  result );
        if(!result){
        	throw new  UtilitiesException(this.getError());
        }
        return result;
    }

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
    
}
