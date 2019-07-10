/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itaca.callcenter.utilities;

import com.itaca.callcenter.constant.PatternsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Validaciones de proveedores
 *
 * @author israel
 */
public class FactoryValidatorQuejas implements FactoryValidator {

    private static final Logger logger = LogManager.getLogger(FactoryValidatorQuejas.class);

    /**
     * Mapa con todos los campos de un registro
     */
    private HashMap<Integer, String> mMapValidators;

    /**
     * Objeto para validaciones basica(campo no vacio)
     */
    private CargaValidator mValidatorBase;

    /**
     * Objeto para validar expresion regular
     */
    private CargaValidator mValidatorRegex;

    /**
     * Objeto para validar que un dato exista en un catalogo
     */
    private CargaValidator mValidatorDS;

    private static FactoryValidatorQuejas INSTANCE = new FactoryValidatorQuejas();

    public FactoryValidatorQuejas() {
        System.out.print("Ejecutando FactoryValidatorQuejas()");
        mMapValidators = new HashMap<Integer, String>();
        mValidatorBase = new CargaValidatorBase();
        mValidatorRegex = new CargaValidatorRegex(mValidatorBase);
        mValidatorDS = new CargaValidatorDSExist(mValidatorRegex);

        mMapValidators.put(1, "causaCarga");
        mMapValidators.put(2, "numero");
        mMapValidators.put(3, "grupo");
        mMapValidators.put(4, "sucursalCarga");
        mMapValidators.put(5, "reportadorCarga");
        mMapValidators.put(6, "cliente");
        mMapValidators.put(7, "fechaNacimiento");
        mMapValidators.put(8, "telefono");
        mMapValidators.put(9, "observacion");
        mMapValidators.put(10, "reportado");
        mMapValidators.put(11, "puestoCarga");
        mMapValidators.put(12, "correoCarga");

    }

    public FactoryValidatorQuejas getInstance() {
        if (INSTANCE == null) {
            return new FactoryValidatorQuejas();
        }
        return INSTANCE;
    }

    public List<CargaValidator> createValidator(int index, EntityManager entityManager) {
        String field;

        field = mMapValidators.get(index);
        logger.info("Campo-[" + field + "]");
        List<CargaValidator> validators = createValidator(field, entityManager); // todo
        // aplicar
        // cambio
        return validators;
    }

    public List<CargaValidator> createValidator(String field, EntityManager entityManager) {
        System.out.print("Crea campo-[" + field + "]");
        List<CargaValidator> validatorList = new ArrayList<CargaValidator>();
        switch (field) {
                case "causaCarga":
//                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
//                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
//                validatorList.add(mValidatorRegex);
                validatorList.add(this.mValidatorBase);
                break;

                case "numero":
                validatorList.add(this.mValidatorBase);
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.DECIMAL,
                        PatternsConstants.ERROR_NUMERICO);
                validatorList.add(mValidatorRegex);
                break;
                
                case "grupo":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

                case "sucursalCarga":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

                case "reportadorCarga":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

                case "cliente":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

                case "fechaNacimiento":
                validatorList.add(this.mValidatorBase);
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.FECHA,
                        PatternsConstants.ERROR_FECHA);
                validatorList.add(mValidatorRegex);
                break;
            
                case "telefono":
                validatorList.add(this.mValidatorBase);
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.FECHA,
                        PatternsConstants.ERROR_FECHA);
                validatorList.add(mValidatorRegex);
                break;
                
                case "observacion":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

                case "reportado":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

                case "puestoCarga":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

                case "correoCarga":
                ((CargaValidatorRegex) mValidatorRegex).setRegex(PatternsConstants.ALFANUMERICO_CON_CARACTERES_ESPECIALES,
                        PatternsConstants.ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES);
                validatorList.add(mValidatorRegex);
                break;

            default:
                validatorList.add(this.mValidatorBase);
                break;
        }
        return validatorList;
    }

    public HashMap<Integer, String> getmMapValidators() {
        return mMapValidators;
    }

    public void setmMapValidators(HashMap<Integer, String> mMapValidators) {
        this.mMapValidators = mMapValidators;
    }

}
