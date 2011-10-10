package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.Proyecto;

/**
 * Servicio que contiene la lógica de negocio de la entidad ObjetivoEspecifico.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ObjetivoEspecificoService extends BusinessEntityService<ObjetivoEspecifico> {

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

	/**
	 * Devuelve todos los objetivos especificos pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<ObjetivoEspecifico> findAllByProyecto(Proyecto proyecto);

	/**
	 * Devuelve todos los objetivos especificos correspondientes a los proyectos que no se encuentren en cierre
	 * ni hayan sido finalizados.
	 * 
	 * @return
	 */
	Collection<ObjetivoEspecifico> findAllNoFinalizadosNiCierre();

}
