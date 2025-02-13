package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;

/**
 * Servicio que contiene la l�gica de negocio de la entidad Actividad.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface ActividadService extends BusinessEntityService<Actividad> {

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
	 * @param actividad actividad cuyo estado se quiere modificar
	 * @param idEstado id del nuevo estado al que se quiere cambiar
	 * @return
	 */
	boolean isTransicionValida(Actividad actividad, Integer idEstado);
	
	/**
	 * Cambia el estado de la actividad al estado del id pasado por parametro. 
	 * 
	 * @param actividad actividad cuyo estado se quiere modificar
	 * @param idEstado id del nuevo estado al que se quiere cambiar
	 */
	void saveCambioEstado(Actividad actividad, Integer idEstado);

	/**
	 * Evalua si el campo completitud se puede modificar o no
	 * 
	 * @param actividad
	 * @return
	 */
	public boolean isActualizarCompletitud(Actividad actividad);

	/**
	 * Determina si la actividad se encuentra suspendida
	 * 
	 * @param actividad
	 * @return
	 */
	boolean isSuspendida(Actividad actividad);
	
	/**
	 * Determina si la actividad se encuentra cancelada
	 * 
	 * @param actividad
	 * @return
	 */
	boolean isCancelada(Actividad actividad);
	
	/**
	 * Determina si la actividad se encuentra cumplida
	 * 
	 * @param actividad
	 * @return
	 */
	boolean isCumplida(Actividad actividad);
	
	/**
	 * Devuelve todas las actividades pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<Actividad> findAllByProyecto(Proyecto proyecto);

	/**
	 * Devuelve todas las actividades no suspendidas pertenecientes al proyecto
	 * 
	 * @param proyecto proyecto
	 * @return
	 */
	Collection<Actividad> findAllNoSuspendidasByProyecto(Proyecto proyecto);
	
	/**
	 * Devuelve todas las actividades correspondientes a los proyectos que no se encuentren finalizados.
	 * 
	 * @return
	 */
	Collection<Actividad> findAllNoFinalizados();

	/**
	 * Devuelve todas las actividades pertenecientes al objetivo general
	 * 
	 * @param objetivoGeneral objetivo general
	 * @return
	 */
	Collection<Actividad> findAllByObjetivoGeneral(ObjetivoGeneral objetivoGeneral);

	/**
	 * Devuelve todas las actividades pertenecientes al objetivo especifico
	 * 
	 * @param objetivoEspecifico objetivo especifico
	 * @return
	 */
	Collection<Actividad> findAllByObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico);

	/**
	 * Devuelve todas las actividades pertenecientes a la meta
	 * 
	 * @param meta meta
	 * @return
	 */
	Collection<Actividad> findAllByMeta(Meta meta);

	/**
	 * Evalua si existen actividades que no se encuentren en un estado final (cumplidas o canceladas) para el proyecto.
	 * 
	 * @param proyecto
	 * @return
	 */
	boolean existenActividadesNoFinalizadas(Proyecto proyecto);
}
