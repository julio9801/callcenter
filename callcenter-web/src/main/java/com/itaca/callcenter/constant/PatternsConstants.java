//****************************************************************************//
// @file PatternsConstants.java
// 
// @description No se conoce el uso de este documento
//****************************************************************************//
package com.itaca.callcenter.constant;

import java.text.SimpleDateFormat;

public class PatternsConstants {
	
	
	public static final String ALFABETICO = "[a-zA-Z\u00F1\u00D1]*";
	public static final String ALFABETICO_CON_ESPACIOS = "[a-zA-Z \u00F1\u00D1]*";
	public static final String ALFABETICO_CON_ESPACIOS_Y_ACENTO = "[a-zA-Z \u00F1\u00D1\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA]*";
	public static final String ALFANUMERICO = "[a-zA-Z0-9]*";
	public static final String ALFANUMERICO_CON_ESPACIOS = "[a-zA-Z0-9 ]*";
	public static final String ALFANUMERICO_CON_AMPERSON = "[a-zA-Z0-9&]*";
	public static final String ALFANUMERICO_CON_CARACTERES_ESPECIALES = "[a-zA-Z0-9 ,\\-\"&$@/_.-\u00F1\u00D1\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA]*";
	public static final String NUMERICO = "[0-9]*";
        public static final String NUMERO_CLABE = "[0-9]{18}";
	public static final String FECHA ="^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)$";
        public static final String FECHA_YYYMMDD ="^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)$";
        public static final String TIME ="^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
	public static final String DECIMAL = "^\\d+(.\\d{1,2})?$";
	public static final String CADENA_PERSONALIDAD_JURIDICA="Física|Moral";
	public static final String CADENA_TIPO_PROVEEDOR="Nacional|Internacional";
	public static final String CADENA_TIPO_DOCUMENTO="Factura";
        SimpleDateFormat FECHA_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static final String ERROR_ALFANUMERICO = "Solo se permiten Letras y números";
	public static final String ERROR_ALFANUMERICO_CON_AMPERSON = "Solo se permiten Letras, números y &(amperson)";
	public static final String ERROR_NUMERICO = "Solo se permiten caracteres numéricos";
	public static final String ERROR_FECHA = "El formato de fecha debe ser dd/mm/yyyy";
	public static final String ERROR_DECIMAL = "Solo se permiten números decimales";
	public static final String ERROR_ALFABETICO = "Solo se permiten letras";
        public static final String ERROR_ALFABETICO_CON_ESPACIOS = "Solo se permiten letras con espacios";
	public static final String ERROR_ALFANUMERICO_CON_CARACTERES_ESPECIALES = "Solo se permiten Letras, números y los caracteres: /\"&@_-.";
	public static final String ERROR_CADENA_PERSONALIDAD_JURIDICA = "Solo se permite Física ó Moral";
	public static final String ERROR_CADENA_TIPO_PROVEEDOR = "Solo se permite Nacional ó Internacional";
	public static final String ERROR_CADENA_TIPO_DOCUMENTO = "Solo se permite Factura";
	public static final String ERROR_TIME = "(00:00)La columna en excel debe estar en formato TEXTO.\n Solo se permite numeros y :";

}
