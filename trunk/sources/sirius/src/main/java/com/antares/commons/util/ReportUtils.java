package com.antares.commons.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.antares.sirius.base.Constants;
import com.antares.sirius.model.FormatoReporte;


/**
 * Clase con utilidades para la generacion de reportes.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 */
public class ReportUtils {

	/**
	 * Genera el array de bytes que sera transformado luego en el reporte a mostrar.
	 * 
	 * @param reportName nombre del reporte.
	 * @param parameters parametros que espera el reporte.
	 * @param items lista de elementos a ser visualizados en el reporte
	 * @return
	 */
	public static JasperPrint generateReportBytes(String reportName, Map<String, String> parameters, Collection items){
		try {
			
			File f = ReportUtils.loadFile(reportName);
			
			JasperDesign design = JRXmlLoader.load(f);
			
			JasperReport report = JasperCompileManager.compileReport(design);
		
			return JasperFillManager.fillReport(report, parameters, new JRListaDataSource(items));
			
		} catch (JRException e) {
			//throw new Exception(e);
			return null;
		} 

	}

	/**
	 * Obtiene el archivo fisico que contiene el diseño del reporte a ser visualizado.
	 * 
	 * @param reportName nombre del reporte/archivo.
	 * @return
	 */	
	private static File loadFile(String reportName){ 			
		
		File file = null;

		String path = ReportUtils.class.getResource("/").getFile();
		int cant;
		cant = path.indexOf("%20");
		while (cant > -1){
			path = path.substring(0,cant) + " " + path.substring(cant+3);
			cant = path.indexOf("%20");
		}
		
		try {
			file = new File(path+"reports/"+reportName +".jrxml");			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return file;
	}
	
	/**
	 * Devuelve la lista de formatos posibles de salida para la generacion de los reportes
	 * @return
	 */
	public static List<FormatoReporte> getReportFormatList(){
		
		List<FormatoReporte> formatos = new ArrayList<FormatoReporte>();
		
		formatos.add(new FormatoReporte(1,Constants.FORMATO_REPORTE_PDF));
		formatos.add(new FormatoReporte(2,Constants.FORMATO_REPORTE_XLS));
		formatos.add(new FormatoReporte(3,Constants.FORMATO_REPORTE_CSV));
		formatos.add(new FormatoReporte(4,Constants.FORMATO_REPORTE_HTM));
		formatos.add(new FormatoReporte(5,Constants.FORMATO_REPORTE_RTF));

		return formatos;
	}

}
