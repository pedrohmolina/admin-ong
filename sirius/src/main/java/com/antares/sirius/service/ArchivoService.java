package com.antares.sirius.service;

import com.antares.commons.service.BaseService;
import com.antares.sirius.model.Archivo;

/**
 * Servicio que contiene la lógica de la entidad Archivo.
 * 
 * @version 1.0.0 Created 28/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ArchivoService extends BaseService<Archivo> {

	/**
	 * Retorna un archivo a partir de un hash
	 * 
	 * @return Archivo correspondiente al hash
	 */
	Archivo findByHash(String hash);

}
