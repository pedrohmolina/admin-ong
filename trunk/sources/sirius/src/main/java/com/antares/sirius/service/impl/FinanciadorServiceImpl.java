package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.FinanciadorDAO;
import com.antares.sirius.model.Financiador;
import com.antares.sirius.service.FinanciadorService;

/**
 * Implementacion de la interfaz FinanciadorService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class FinanciadorServiceImpl extends BusinessEntityServiceImpl<Financiador, FinanciadorDAO> implements FinanciadorService {

	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido = false;
		Financiador entity = dao.findByNombre(nombre);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

}
