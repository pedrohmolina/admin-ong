/**
 *
 */
package com.antares.sirius.base;


public interface Constants {

	String CONFIGURATION_DIR = "sirius.configuration.dir";
	String CONFIGURATION_FILE = "configuration.properties";
	String CONFIGURATION_LOG = "log4j.properties";
	String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	String DEFAULT_DECIMAL_FORMAT = "#0.00";

	//Constantes vinculadas a reportes
	String FORMATO_REPORTE_PDF = "pdf";
	String FORMATO_REPORTE_XLS = "xls";
	String FORMATO_REPORTE_CSV = "csv";
	String FORMATO_REPORTE_HTM = "htm";
	String FORMATO_REPORTE_RTF = "rtf";
	
	String REPORTE_PERSONAS = "ReportePersonas";
	String REPORTE_FINANCIADORES = "ReporteFinanciadores";
	String REPORTE_PROVEEDORES = "ReporteProveedores";
	String REPORTE_FINANCIERO = "ReporteFinanzas";
	
	String REPORTE_FINANCIERO_VISUALIZACION_PORCENTUAL = "porcentual";
	String REPORTE_FINANCIERO_VISUALIZACION_POR_MONTOS = "montos";
	
	String ANTARES_LOGO = "/reports/Antares.jpg";
}