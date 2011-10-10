package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;

/**
 * Servicio que contiene la l�gica de negocio de la entidad ObjetivoGeneral.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ObjetivoGeneralService extends BusinessEntityService<ObjetivoGeneral> {

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param nombre nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String nombre, Integer id);

	/**
	 * Devuelve todos los objetivos generales pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<ObjetivoGeneral> findAllByProyecto(Proyecto proyecto);

	/**
	 * Devuelve todos los objetivos generales correspondientes a los proyectos que no se encuentren en cierre
	 * ni hayan sido finalizados.
	 * 
	 * @return
	 */
	Collection<ObjetivoGeneral> findAllNoFinalizadosNiCierre();

}
