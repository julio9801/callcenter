package com.itaca.callcenter.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Clase que lee de una fuente de datos los registros.
 * 
 */
public abstract class ReaderRegistros 
{
    protected int m_numberOfReadedRegisters;
    
    protected InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }
    
    /**
     * Constructor 
     * 
     * @param inputStream el archivo que contiene los datos de los registros
     */
    public ReaderRegistros(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }
    
    /**
     * Regresa el número de registros de la fuente de datos
     * @return numero de registros contenidos en la fuente de datos
     */
    public int getNumberOfRegisters()
    {
        return m_numberOfReadedRegisters;
    }
    
    /**
     * Lee los registros de una fuente de datos
     * 
     * @return una lista de registros o nulo si ocurrió un error al leer desde 
     *         el archivo
     */
    public abstract List<String> readRegistros();    
    public abstract List<String> readRegistro() throws IOException;    
}
