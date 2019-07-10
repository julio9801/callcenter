package com.itaca.callcenter.utilities;

import com.itaca.callcenter.domain.entities.Queja;
import com.itaca.callcenter.web.utils.DateUtils;
import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase para armar al objeto proveedor
 *
 * @author HE SA
 *
 */
public class QuejasObject extends ObjectEntity {

    private static final Logger logger = LogManager.getLogger(QuejasObject.class);

    private Queja queja;

    public QuejasObject(Queja queja) {
        this.queja = queja;
    }

    public QuejasObject() {

    }

    public void inicializarObjeto() {
        queja = new Queja();
    }

    /**
     * {@inheritDoc}
     */
    public Queja crearObjeto(HashMap<Integer, String> mMapValidators, HashMap<Integer, String> mMapDatosPago) {
        inicializarObjeto();
        mMapDatosPago.forEach((k, v) -> llenarCampoPago(k, v, mMapValidators));
        System.out.print("Queja done?");
        System.out.print(queja);
        return queja;
    }

    /**
     * Llena campos del objeto Proveedor
     *
     * @param index
     * @param value
     * @param mMapValidators
     */
    public void llenarCampoPago(int index, String value, HashMap<Integer, String> mMapValidators) {
        //logger.info("Ejecutando llenarCampoPago()");
        System.out.print("llenarCampo");
        String field = mMapValidators.get(index);
        BigDecimal val;
        switch (field) {
            case "causaCarga":
                queja.setCausaCarga(value);
                break;
            case "numero":
                val = new BigDecimal(value);
                queja.setNumero(val);
                break;
            case "grupo":
                queja.setGrupo(value);
                break;
            case "sucursalCarga":
                queja.setSucursalCarga(value);
                break;
            case "reportadorCarga":
                queja.setReportadorCarga(value);
                break;
            case "cliente":
                queja.setCliente(value);
                break;
            case "fechaNacimiento":
                queja.setFechaNacimiento(DateUtils.toDateFormat_dd_MM_yyyy(value));
                break;
            case "telefono":
                queja.setTelefono(value);
                break;
            case "observacion":
                queja.setObservacion(value);
                break;
            case "reportado":
                queja.setReportado(value);
                break;
            case "puestoCarga":
                queja.setPuestoCarga(value);
                break;
            case "correoCarga":
                queja.setCorreoCarga(value);
                break;

            default:
                break;
        }
    }

}
