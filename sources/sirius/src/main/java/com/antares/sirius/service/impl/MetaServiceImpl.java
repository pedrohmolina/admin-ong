package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.MetaDAO;
import com.antares.sirius.model.Meta;
import com.antares.sirius.service.MetaService;

/**
 * Implementacion de la interfaz MetaService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class MetaServiceImpl extends BusinessEntityServiceImpl<Meta, MetaDAO> implements MetaService {

	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido;
		if (id == null) {
			isNombreRepetido = dao.findByNombre(nombre) != null;
		} else {
			isNombreRepetido = !dao.findByNombre(nombre).getId().equals(id);
		}
		return isNombreRepetido;
	}

}
