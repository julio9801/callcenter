package com.itaca.callcenter.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesUtil {
	
	private static final Logger logger = LogManager.getLogger(PropertiesUtil.class);
	
	/**
	 * obtiene propiedad de application.properties
	 * @param property
	 * @return
	 */
	public static String getProperty(String property) throws Exception {
		logger.info("Ejecutando getProperty()");
		String value = null;
		Properties prop = new Properties();
		InputStream input = null;
		if (property != null && !property.isEmpty()) {
			try {
				String filename = "properties/web-application.properties";
				input = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
				if (input != null) {
					prop.load(input);
					value = prop.getProperty(property);
					logger.info("value: " + value);
				}

			} catch (IOException ex) {
				logger.error("Error: " + ex.getMessage());
				logger.error("Error: " + ex.toString());
				ex.printStackTrace();
				throw ex;
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
						throw e;
					}
				}
			}
		}
		return value;
	}

}
