package com.antares.sirius.service;

import java.util.Collection;

import net.sf.jasperreports.engine.JasperPrint;

import com.antares.commons.exception.ServiceException;
import com.antares.sirius.model.Financiador;

/**
 * Servicio que contiene la logica necesaria para la generacion del reporte de Financiadores.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
public interface ReporteFinanciadorService {

	/**
	 * Devuelve un array de bytes que representa la informacion que sera visualizada en el reporte a ser generado.
	 * 
	 * @param financiadores coleccion de elementos a ser visualizada en el reporte
	 * @return
	 */
	public JasperPrint generateReportBytes(Collection<Financiador> financiadores) throws ServiceException;

	}
