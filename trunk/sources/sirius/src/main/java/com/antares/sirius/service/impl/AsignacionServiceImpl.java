package com.antares.sirius.service.impl;

import java.util.Collection;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.AsignacionDAO;
import com.antares.sirius.filter.AsignacionFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Asignacion;
import com.antares.sirius.model.Persona;
import com.antares.sirius.model.TipoAsignacion;
import com.antares.sirius.service.AsignacionService;

/**
 * Implementacion de la interfaz AsignacionService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class AsignacionServiceImpl extends BusinessEntityServiceImpl<Asignacion, AsignacionDAO> implements AsignacionService {
	
	public boolean isAsignacionRepetida(Integer id, Actividad actividad, Persona persona, TipoAsignacion tipoAsignacion) {
		boolean isAsignacionRepetida = false;
		AsignacionFilter filter = new AsignacionFilter();
		filter.setActividad(actividad);
		filter.setPersona(persona);
		filter.setTipoAsignacion(tipoAsignacion);
		Collection<Asignacion> asignaciones = dao.findByFilter(filter); 
		if (!asignaciones.isEmpty()) {
			if (id != null) {
				for (Asignacion asignacion : asignaciones) {
					if (!asignacion.getId().equals(id)) {
						isAsignacionRepetida = true;
					}
				}
			} else {
				isAsignacionRepetida = true;
			}
		}
		return isAsignacionRepetida;
	}

}
