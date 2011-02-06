package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.ObjetivoGeneralDAO;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.service.ObjetivoGeneralService;

/**
 * Implementacion de la interfaz ObjetivoGeneralService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ObjetivoGeneralServiceImpl extends BusinessEntityServiceImpl<ObjetivoGeneral, ObjetivoGeneralDAO> implements ObjetivoGeneralService {

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
