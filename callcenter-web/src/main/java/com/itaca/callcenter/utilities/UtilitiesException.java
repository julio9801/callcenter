/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templatestor.
 */
package com.itaca.callcenter.utilities;

import java.util.List;

/**
 *
 * @author israel
 */
public class UtilitiesException extends Exception
{
    List<String> errors;
     public UtilitiesException() { super(); }
     public UtilitiesException(String message) { super(message); }
        public UtilitiesException(String message, List<String> errors) 
        { super(message); 
            this.errors =  errors;
        }
        
     public List<String> getErrors(){
        return this.errors;
     }
        
     
     public UtilitiesException(String message, Throwable cause) { super(message, cause); }
     public UtilitiesException(Throwable cause) { super(cause); }
}
