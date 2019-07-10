package com.itaca.callcenter.utilities;

import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 */
public abstract class ParserRegistros<T>
{
    //Reader de registros
    protected ReaderRegistros m_readerRegistros;
    protected EntityManager m_entityManager;
    
    
    public ParserRegistros(ReaderRegistros reader, EntityManager entityManager)
    {
    	m_readerRegistros = reader;
        m_entityManager = entityManager;
    }
    
    public ReaderRegistros getReaderRegistros()
    {
        return this.m_readerRegistros;
    }
    
    public abstract List<T> parse( FactoryValidator fv) throws UtilitiesException;
    
}
