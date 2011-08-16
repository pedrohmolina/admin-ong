package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Notificacion;

public interface NotificacionDAO extends BusinessEntityDAO<Notificacion> {

	/**
	 * Devuelve todas las notificaciones por nombre de usuario
	 * 
	 * @param username nombre del usuario
	 * @return
	 */
	Collection<Notificacion> findByUsername(String username);

	/**
	 * Devuelve las notificaciones por nombre de usuario y estado
	 * 
	 * @param username nombre del usuario
	 * @param leidas señal que indica si se deben traer las notificaciones leidas o no leidas, 
	 * 		en caso de ser null no se filtra por este campo 
	 * @return
	 */
	Collection<Notificacion> findByUsername(String username, Boolean leidas);

}
