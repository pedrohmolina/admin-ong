package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.ObjetivoEspecificoDAO;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.service.ObjetivoEspecificoService;

/**
 * Implementacion de la interfaz ObjetivoEspecificoService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ObjetivoEspecificoServiceImpl extends BusinessEntityServiceImpl<ObjetivoEspecifico, ObjetivoEspecificoDAO> implements ObjetivoEspecificoService {

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
