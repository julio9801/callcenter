package com.itaca.callcenter.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Clase concreta que servira para leer los registros tomando un archivo como 
 * fuente de datos
 * 
 * 
 */
public class ReaderRegistrosFile extends ReaderRegistros
{
	private static final Logger logger = LogManager.getLogger(ReaderRegistrosFile.class);
    //Caracter al incio de una linea el cual indica que la linea sera ignorada
    private String m_ignoreLineChar;
    private boolean m_header=true;
    
    private BufferedReader bufferReader;
    
    /**
     * Constructor 
     * 
     * @param inputStream el archivo que contiene los datos de los registros
     */
    public ReaderRegistrosFile(InputStream inputStream)
    {
        this("#", inputStream);
    }
    
    /**
     * Constructor
     * @param ignoreLineChar caracter al incio de una linea el cual indica que la linea sera ignorada
     */
    public ReaderRegistrosFile(String ignoreLineChar, InputStream inputStream)
    {
        super(inputStream);
        m_ignoreLineChar = ignoreLineChar;
    }
    
    /**
     * Lee los provedores de un archivo especificado por una ruta en el filesystem
     * 
     * @param fileToRead el archivo que contiene los datos de los proveedores
     * @return una lista de proveedores o nulo si ocurrió un error al leer desde 
     *         el archivo
     */
    public List<String> readRegistros()
    {
    	logger.info("ejecutando readRegistros()");
        List<String> listaRegistros = new ArrayList<String>();
        String proveedorLine;
        try
        {
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            if(m_header)
                bufferReader.readLine();// se descarta la primera linea.
            
            while ((proveedorLine = bufferReader.readLine()) != null) 
            {
                if(!proveedorLine.startsWith(m_ignoreLineChar))
                {
                    this.m_numberOfReadedRegisters ++;
                    listaRegistros.add(proveedorLine);
                }
            }
            return listaRegistros;
        }
        catch(FileNotFoundException fnfe)
        {
        	logger.info("ReadProveedores Error: " + fnfe.getMessage());
            return null;
        }
        catch(IOException ioe)
        {
        	logger.info("ReadProveedores Error: " + ioe.getMessage());
            return null;
        }
    }

    @Override
    public List<String> readRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
