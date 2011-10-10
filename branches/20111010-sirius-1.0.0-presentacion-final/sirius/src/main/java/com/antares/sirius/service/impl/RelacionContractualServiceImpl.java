package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.RelacionContractualDAO;
import com.antares.sirius.model.RelacionContractual;
import com.antares.sirius.service.RelacionContractualService;

/**
 * Implementacion de la interfaz RelacionContractualService.
 *
 * @version 1.0.0 Created 22/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class RelacionContractualServiceImpl extends BusinessEntityServiceImpl<RelacionContractual, RelacionContractualDAO> implements RelacionContractualService {

	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido = false;
		RelacionContractual entity = dao.findByNombre(nombre);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

}
