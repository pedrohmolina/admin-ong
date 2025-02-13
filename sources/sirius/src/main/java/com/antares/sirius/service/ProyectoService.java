package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Proyecto;

/**
 * Servicio que contiene la l�gica de negocio de la entidad Proyecto.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ProyectoService extends BusinessEntityService<Proyecto> {

	/**
	 * Devuelve todos los proyectos todavia no finalizados
	 * 
	 * @return
	 */
	Collection<Proyecto> findAllNoFinalizados();

	/**
	 * Devuelve todos los proyectos todavia no finalizados ni en cierre
	 * 
	 * @return
	 */
	Collection<Proyecto> findAllNoFinalizadosNiCierre();

	/**
	 * Determina si el proyecto se encuentra finalizado
	 * 
	 * @param proyecto
	 * @return
	 */
	boolean isFinalizado(Proyecto proyecto);

	/**
	 * Determina si el proyecto se encuentra en cierre
	 * 
	 * @param proyecto
	 * @return
	 */
	boolean isCierre(Proyecto proyecto);

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

	/**
	 * Evalua si la transicion de estados es v�lida
	 * 
	 * @param proyecto proyecto cuyo estado se quiere modificar
	 * @param idEstado id del nuevo estado al que se quiere cambiar
	 * @return
	 */
	boolean isTransicionValida(Proyecto proyecto, Integer idEstado);
	
	/**
	 * Cambia el estado del proyecto al estado del id pasado por parametro. 
	 * 
	 * @param proyecto proyecto cuyo estado se quiere modificar
	 * @param idEstado id del nuevo estado al que se quiere cambiar
	 */
	void saveCambioEstado(Proyecto proyecto, Integer idEstado);

	/**
	 * Determina si el proyecto soporta carga de gastos individual
	 * 
	 * @param proyecto
	 * @return
	 */
	boolean isIndividual(Proyecto proyecto);

	/**
	 * Determina si el proyecto soporta carga de gastos agrupada
	 * 
	 * @param proyecto
	 * @return
	 */
	boolean isAgrupado(Proyecto proyecto);

}
