package com.antares.sirius.service;

import java.util.Collection;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Notificacion;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Usuario;

/**
 * Servicio que contiene la lógica de negocio de la entidad Notificacion.
 * 
 * @version 1.0.0 Created 14/08/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface NotificacionService extends BusinessEntityService<Notificacion> {

	/**
	 * Devuelve todas las notificaciones del usuario logueado
	 */
	Collection<Notificacion> findAll();

	/**
	 * Devuelve todas las notificaciones no leidas del usuario logueado
	 */
	Collection<Notificacion> findAllNoLeidas();

	/**
	 * Devuelve todas las notificaciones leidas del usuario logueado
	 */
	Collection<Notificacion> findAllLeidas();

	/**
	 * Marca la notificacion como leida
	 * 
	 * @param entity
	 */
	void ejecutarMarcarLeida(Notificacion entity);

	/**
	 * Envia una notificacion al usuario 
	 * 
	 * @param usuario usuario al que se desea notificar
	 * @param proyecto proyecto al que referencia la notificacion (opcional)
	 * @param mensaje texto de la notificacion
	 */
	void ejecutarNotificacion(Usuario usuario, Proyecto proyecto, String mensaje);

	/**
	 * Envia una notificacion de gasto excedido de proyecto a los usuarios que tienen acceso para confirmarlos 
	 * 
	 * @param proyecto proyecto al que referencia la notificacion (opcional)
	 * @param mensaje texto de la notificacion
	 */
	void ejecutarNotificacionGastoProyectoExcedido(Proyecto proyecto, String mensaje);

	/**
	 * Envia una notificacion de gasto excedido de actividad a los usuarios que tienen acceso para confirmarlos 
	 * 
	 * @param proyecto
	 * @param mensaje
	 */
	void ejecutarNotificacionGastoActividadExcedido(Proyecto proyecto, String mensaje);

}
