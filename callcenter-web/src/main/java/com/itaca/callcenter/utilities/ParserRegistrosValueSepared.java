/*
 */
package com.itaca.callcenter.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 
 * @author israel
 *
 * @param <T> Object
 * @param <U> ProveedorObject(OBJECT) todo
 */
public class ParserRegistrosValueSepared<T> extends ParserRegistros<T> {

	private static final Logger logger = LogManager.getLogger(ParserRegistrosValueSepared.class);
	private String separator;
	private int numValidoColumnas;
	private ObjectEntity objectEntity;

	public ParserRegistrosValueSepared(ReaderRegistros reader, EntityManager entityManager, ObjectEntity objectEntity, int numValidoColumnas) {
		super(reader, entityManager);
		try {
			this.numValidoColumnas  = numValidoColumnas ;
			this.separator ="|";
			this.objectEntity = objectEntity;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ParserRegistrosValueSepared(ReaderRegistros reader, String separator, EntityManager entityManager, ObjectEntity objectEntity, int numValidoColumnas ) throws NumberFormatException, Exception {
		this(reader, entityManager, objectEntity, numValidoColumnas);
		this.separator = separator;
	}

	@SuppressWarnings("unchecked")
	public List<T> parse(FactoryValidator fv) throws UtilitiesException {
		logger.info("Ejecutando parse()");
		HashMap<Integer, String> mMapDatosProveedor = new HashMap<Integer, String>();
		HashMap<Integer, String> mMapValidators = null;
		List<T> objetos = new ArrayList<>();
		List<CargaValidator> validatorList;
		FactoryValidator factoryValidator = fv.getInstance();
		int numeroRegistro = 0;
		String[] tokens;
		String token;
		List<String> registros = this.m_readerRegistros.readRegistros();
		List<String> errors = new ArrayList<String>();
		for (String registro : registros) {
			logger.info("registro: " + registro);
			numeroRegistro++;
			tokens = registro.split("\\" + separator, -1);
			if (tokens.length != numValidoColumnas) {
				logger.info("Numero invalido de columnas");
				throw new UtilitiesException("Numero de columnas no valido en el registro " + numeroRegistro
						+ " se encontraron [" + tokens.length + "] de [" + numValidoColumnas + "]");
			}
			// por cada token (posicional)
			for (int index = 0; index < tokens.length; index++) {
				token = tokens[index];
				validatorList = factoryValidator.createValidator(index + 1, this.m_entityManager);
				mMapValidators = factoryValidator.getmMapValidators();
				logger.info("Validando token: " + token);
				try {
					for(CargaValidator validator: validatorList){
						validator.validate(token);
					}
					mMapDatosProveedor.put(index + 1, token);

				} catch (UtilitiesException e) {
					errors.add("|Registro " + numeroRegistro + "-" + mMapValidators.get(index + 1) +":" + tokens[index] + "," + e.getMessage() + "|");
				}
			}
			//Si el numero de columnas es valido se arma el objeto proveedor y se agrega a la lista de registros
			T t =  (T) objectEntity.crearObjeto(mMapValidators, mMapDatosProveedor);
			objetos.add(t);
		}
		
		//Si hay errores de validacion, se lanza excepcion
		if(errors == null || !errors.isEmpty()){
			StringBuilder message = new StringBuilder();
	     	for(String error: errors){
	     		message.append(error);
	     		message.append(System.getProperty("line.separator"));
	     	}
	     	logger.info("Mensaje de errores: " + message);
			throw new UtilitiesException(message.toString());
		}
		
		return objetos;
	}

}
