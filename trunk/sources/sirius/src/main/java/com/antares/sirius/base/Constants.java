/**
 *
 */
package com.antares.sirius.base;


public interface Constants {

	String CHANGE_PASSWORD_ROLE = "CAMBIAR-PASSWORD";

	String CONFIGURATION_DIR = "sirius.configuration.dir";
	String CONFIGURATION_FILE = "configuration.properties";
	String CONFIGURATION_LOG = "log4j.properties";
	String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	String DEFAULT_DECIMAL_FORMAT = "#0.00";

	String ID_TIPO_INGRESO_FINANCIACION = "3"; //FIXME Hardcode HORRIBLE ver de pasarlo a una tabla de parametros
	Integer ID_ESTADO_PROYECTO_IDEA = 1; //FIXME Hardcode HORRIBLE ver de pasarlo a una tabla de parametros
	Integer ID_ESTADO_ACTIVIDAD_LATENTE = 1; //FIXME Hardcode HORRIBLE ver de pasarlo a una tabla de parametros
	Integer ID_ESTADO_ACTIVIDAD_PROGRESO = 2; //FIXME Hardcode HORRIBLE ver de pasarlo a una tabla de parametros

}