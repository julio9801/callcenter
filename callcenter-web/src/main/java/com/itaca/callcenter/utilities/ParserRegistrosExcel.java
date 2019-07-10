/*
 */
package com.itaca.callcenter.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author israel
 *
 * @param <T> Object
 * @param <U> ProveedorObject(OBJECT) todo
 */
public class ParserRegistrosExcel<T> extends ParserRegistros<T> {

    private static final Logger logger = LogManager.getLogger(ParserRegistrosExcel.class);

    private int numValidoColumnas;
    private ObjectEntity objectEntity;

    public ParserRegistrosExcel(ReaderRegistros reader, EntityManager entityManager, ObjectEntity objectEntity, int numValidoColumnas) {
        super(reader, entityManager);
        System.out.print("Excel Parser");
        try {
            this.numValidoColumnas = numValidoColumnas;

            this.objectEntity = objectEntity;
        } catch (Exception e) {
            System.out.print("Truena");
            System.out.print(e.toString() + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> parse(FactoryValidator fv) throws UtilitiesException {
        try {
            logger.info("Ejecutando parse()");
            List<String> errors = new ArrayList<String>();
            HashMap<Integer, String> mMapDatos = new HashMap<Integer, String>();
            HashMap<Integer, String> mMapValidators = null;
            List<T> objetos = new ArrayList<>();
            List<CargaValidator> validatorList;
            FactoryValidator factoryValidator = fv.getInstance();
            int numeroRegistro = 0;

            List<String> registro;
            System.out.print(":::::::::::::::::::");
            while ((registro = m_readerRegistros.readRegistro()) != null) {
                System.out.print(registro);
                numeroRegistro++;
                if (registro.size() != numValidoColumnas) {
                    logger.info("Numero invalido de columnas");
                    throw new UtilitiesException("Numero de columnas no valido en el registro " + (numeroRegistro + 1)
                            + " se encontraron [" + registro.size() + "] de [" + numValidoColumnas + "]");
                }
                int index = 0;
                for (String token : registro) {
                    validatorList = factoryValidator.createValidator(index + 1, this.m_entityManager);
                    mMapValidators = factoryValidator.getmMapValidators();
                    System.out.print("Validando: " + token);
                    try {
                        for (CargaValidator validator : validatorList) {
                            validator.validate(token);
                        }
                        mMapDatos.put(index + 1, token);

                    } catch (UtilitiesException e) {
                        System.out.print("|Registro " + (numeroRegistro + 1) + "- Columna " + mMapValidators.get(index + 1) + " : " + token + " , " + e.getMessage() + " |");
                    }
                    index++;

                }

                T t = (T) objectEntity.crearObjeto(mMapValidators, mMapDatos);
                objetos.add(t);
            }
            System.out.print("::::::::::::::::::: Out of while");
            System.out.print(objetos);
            //Si hay errores de validacion, se lanza excepcion
            if (errors.isEmpty() == false) {
                logger.info("Mensaje de errores: " + errors);
                throw new UtilitiesException("", errors);
            }

            return objetos;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new UtilitiesException(ex.getMessage());
        }
    }

}
