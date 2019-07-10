package com.itaca.callcenter.utilities;

import com.itaca.callcenter.domain.entities.Queja;
import java.io.InputStream;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Fabrica para crear parsers
 *
 */
public class FactoryParser {

    private static final Logger logger = LogManager.getLogger(FactoryParser.class);

    private static FactoryParser INSTANCE = new FactoryParser();

    private FactoryParser() {
    }

    public static FactoryParser getInstance() {
        if (INSTANCE == null) {
            return new FactoryParser();
        }
        return INSTANCE;
    }

    /**
     * Crea parser 
     * @param inputStream
     * @param entityManager
     * @param quejaObject
     * @param numValidoColumnas
     * @return
     * @throws UtilitiesException
     */
    public ParserRegistros<Queja> createParserQuejas(InputStream inputStream, EntityManager entityManager, QuejasObject quejaObject, int numValidoColumnas) throws UtilitiesException {
        System.out.print("Ejecutando createParserQuejas()");
        if (inputStream == null) {
            throw new UtilitiesException("InputStream can not be null");
        }
        ReaderRegistros readerFile = new ReaderExcelFile(inputStream);
        ParserRegistros<Queja> parser = new ParserRegistrosExcel<Queja>(readerFile, entityManager, quejaObject, numValidoColumnas);
        System.out.print("Regresa parser");
        return parser;
    }
}
