package com.itaca.callcenter.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * Clase concreta que servira para leer los registros tomando un archivo como
 * fuente de datos
 *
 *
 */
public class ReaderExcelFile extends ReaderRegistros {

    private static final Logger logger = LogManager.getLogger(ReaderExcelFile.class);
   private  SimpleDateFormat FECHA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    //Caracter al incio de una linea el cual indica que la linea sera ignorada
    private String m_ignoreLineChar;
    private boolean m_header = true;

    private BufferedReader bufferReader;
    Workbook workbook=null;
    int pos = 0, lastRow;
    Sheet datatypeSheet;

    /**
     * Constructor
     *
     * @param inputStream el archivo que contiene los datos de los registros
     */
    public ReaderExcelFile(InputStream inputStream) {
        this("#", inputStream);
    }

    /**
     * Constructor
     *
     * @param ignoreLineChar caracter al incio de una linea el cual indica que
     * la linea sera ignorada
     */
    public ReaderExcelFile(String ignoreLineChar, InputStream inputStream) {
        super(inputStream);
        m_ignoreLineChar = ignoreLineChar;
        if (m_header == true) {
            pos++;
        }

    }

    private void init() throws IOException {
        workbook = new XSSFWorkbook(inputStream);
        datatypeSheet = workbook.getSheetAt(0);
        this.lastRow=datatypeSheet.getLastRowNum();
        
    }

    @Override
    public List<String> readRegistro() throws IOException {

        List<String> listaRegistros = new ArrayList<String>();
        if(workbook==null){
            init();
        }
        if(pos>this.lastRow)
            return null;
       
        Iterator<Cell>  celdas= datatypeSheet.getRow(pos).cellIterator();
        
        while(celdas.hasNext()){
            Cell celda= celdas.next();
           if(celda.getCellTypeEnum()==CellType.NUMERIC && HSSFDateUtil.isCellDateFormatted(celda)){
               listaRegistros.add(FECHA_FORMAT.format(celda.getDateCellValue() ));
           }
           else if(celda.getCellTypeEnum()==CellType.NUMERIC){
               listaRegistros.add( Double.toString(celda.getNumericCellValue()) );
                
            }else if(celda.getCellTypeEnum()==CellType.STRING)
              listaRegistros.add(celda.getStringCellValue());
           
            
        }
        pos++;
        return listaRegistros;
    }

    /**
     * Lee los provedores de un archivo especificado por una ruta en el
     * filesystem
     *
     * @param fileToRead el archivo que contiene los datos de los proveedores
     * @return una lista de proveedores o nulo si ocurrió un error al leer desde
     * el archivo
     */
    public List<String> readRegistros() {
        logger.info("ejecutando readRegistros()");
        List<String> listaRegistros = new ArrayList<String>();
        String proveedorLine;
        try {
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            if (m_header) {
                bufferReader.readLine();// se descarta la primera linea.
            }
            while ((proveedorLine = bufferReader.readLine()) != null) {
                if (!proveedorLine.startsWith(m_ignoreLineChar)) {
                    this.m_numberOfReadedRegisters++;
                    listaRegistros.add(proveedorLine);
                }
            }
            return listaRegistros;
        } catch (FileNotFoundException fnfe) {
            logger.info("ReadProveedores Error: " + fnfe.getMessage());
            return null;
        } catch (IOException ioe) {
            logger.info("ReadProveedores Error: " + ioe.getMessage());
            return null;
        }
    }
}
