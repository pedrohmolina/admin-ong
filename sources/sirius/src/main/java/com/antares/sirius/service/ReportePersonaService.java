package com.antares.sirius.service;

import java.util.Collection;

import net.sf.jasperreports.engine.JasperPrint;

import com.antares.commons.exception.ServiceException;
import com.antares.sirius.model.Persona;

/**
 * Servicio que contiene la logica necesaria para la generacion del reporte de Personas.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
public interface ReportePersonaService {

	/**
	 * Devuelve un array de bytes que representa la informacion que sera visualizada en el reporte a ser generado.
	 * 
	 * @param personas coleccion de elementos a ser visualizada en el reporte
	 * @return
	 */
	public JasperPrint generateReportBytes(Collection<Persona> personas) throws ServiceException;

	}
