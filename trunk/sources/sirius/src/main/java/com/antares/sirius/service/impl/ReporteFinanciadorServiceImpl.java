package com.antares.sirius.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import com.antares.commons.exception.ServiceException;
import com.antares.commons.util.ReportUtils;
import com.antares.sirius.base.Constants;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.model.Persona;
import com.antares.sirius.service.ReporteFinanciadorService;

/**
 * Implementacion de la interfaz ReporteFinanciadorService.
 *
 * @version 1.0.0 Created 23/04/2011 by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com> Pablo Delfino </a>
 *
 */
public class ReporteFinanciadorServiceImpl implements ReporteFinanciadorService{

	public JasperPrint generateReportBytes(Collection<Financiador> financiadores) throws ServiceException{

		try {
			
			Map<String, String> parametros = new HashMap<String, String>();
			//parametros.put("nombreParametroReporte", String.valueOf(...));

			//List listaPersonas = this.cargarPersonas(personas);
			
			return ReportUtils.generateReportBytes(Constants.REPORTE_FINANCIADORES, parametros, financiadores);
		
		} catch (Exception e) {
			throw new ServiceException("Error al generar reporte de Personas.");
		}
	}
	
	private List cargarPersonas(Collection<Persona> personas) {

		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		Iterator<Persona> itPersona = personas.iterator();
		
		while (itPersona.hasNext()) {
			Persona persona = (Persona) itPersona
					.next();

			HashMap<String, String> registroPersona = new HashMap<String, String>();
			registroPersona.put("persona_nombre",persona.getNombre());
			registroPersona.put("persona_apellido",persona.getApellido());
			registroPersona.put("persona_numeroDocumento",persona.getNumeroDocumento().toString());
			registroPersona.put("persona_nacionalidad",persona.getNacionalidad());
			registroPersona.put("persona_cbu",persona.getCbu());

			result.add(registroPersona);
		}

		return result;
	}
}
