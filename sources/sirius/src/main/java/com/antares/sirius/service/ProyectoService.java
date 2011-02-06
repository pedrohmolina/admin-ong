package com.antares.sirius.service;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Proyecto;

/**
 * Servicio que contiene la lógica de negocio de la entidad Proyecto.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ProyectoService extends BusinessEntityService<Proyecto> {

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

	/**
	 * Cambia el estado del proyecto al estado del id pasado por parametro. 
	 * En caso que la transicion de estados no sea valida, no realiza ninguna modificacion sobre el proyecto
	 * 
	 * @param proyecto proyecto cuyo estado se quiere modificar
	 * @param idEstado id del nuevo estado al que se quiere cambiar
	 */
	void saveCambioEstado(Proyecto proyecto, Integer idEstado);

}
