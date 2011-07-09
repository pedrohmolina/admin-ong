package com.antares.sirius.service;

import java.util.Collection;

import net.sf.jasperreports.engine.JasperPrint;

import com.antares.commons.exception.ServiceException;
import com.antares.sirius.model.Presupuesto;


/**
 * Servicio que contiene la logica necesaria para la generacion del reporte de Finanzas.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
public interface ReporteFinancieroService {

	/**
	 * Metodo que genera los bytes que representan el reporte a ser visualizado.
	 * @param presupuestos lista de presupuestos
	 * @return JasperPrint
	 * @throws ServiceException
	 */
	public JasperPrint generateReportBytes(Collection<Presupuesto> presupuestos) throws ServiceException;

}
