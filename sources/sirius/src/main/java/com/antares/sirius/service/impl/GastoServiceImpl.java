package com.antares.sirius.service.impl;

import static java.lang.Boolean.TRUE;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.GastoDAO;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.service.GastoService;

/**
 * Implementacion de la interfaz GastoService.
 *
 * @version 1.0.0 Created 16/02/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class GastoServiceImpl extends BusinessEntityServiceImpl<Gasto, GastoDAO> implements GastoService {

	public void ejecutarConfirmacion(Gasto entity) {
		entity.setConfirmado(TRUE);
		dao.save(entity);
	}

}
