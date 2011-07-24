package com.antares.sirius.service.impl;

import java.util.Collection;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.PersonaDAO;
import com.antares.sirius.model.Persona;
import com.antares.sirius.service.PersonaService;

/**
 * Implementacion de la interfaz PersonaService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class PersonaServiceImpl extends BusinessEntityServiceImpl<Persona, PersonaDAO> implements PersonaService {

	public boolean isNombreApellidoRepetido(String nombre, String apellido, Integer id) {
		boolean isNombreRepetido = false;
		Persona entity = dao.findByNombreApellido(nombre, apellido);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

	public Collection<Persona> findAllOthers(Integer id) {
		return dao.findAllOthers(id);
	}

}
