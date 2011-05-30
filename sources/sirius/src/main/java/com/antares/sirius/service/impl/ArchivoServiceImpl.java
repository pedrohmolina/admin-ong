package com.antares.sirius.service.impl;

import com.antares.commons.service.impl.BaseServiceImpl;
import com.antares.sirius.dao.ArchivoDAO;
import com.antares.sirius.model.Archivo;
import com.antares.sirius.service.ArchivoService;

/**
 * Implementacion de la interfaz ArchivoService.
 *
 * @version 1.0.0 Created 28/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ArchivoServiceImpl extends BaseServiceImpl<Archivo, ArchivoDAO> implements ArchivoService {

	@Override
	public Archivo findByHash(String hash) {
		return dao.findByHash(hash);
	}

}
