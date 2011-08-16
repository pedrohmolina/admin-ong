package com.antares.sirius.service;

import java.util.Collection;

import org.acegisecurity.userdetails.UserDetailsService;

import com.antares.commons.service.BusinessEntityService;
import com.antares.sirius.model.Acceso;
import com.antares.sirius.model.Usuario;

/**
 * Servicio que contiene la lógica de negocio de la entidad Usuario.
 * 
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public interface UsuarioService extends BusinessEntityService<Usuario>, UserDetailsService {

	/**
	 * Valida que el nombre no este repetido otra entidad con distinto id
	 * 
	 * @param username nombre a validar
	 * @param id id de la entidad actual
	 * @return
	 */
	boolean isNombreRepetido(String username, Integer id);

	/**
	 * Devuelve un Usuario a partir de su username
	 * @return Usuario usuario encontrado o null, en caso de que no exista
	 */
	Usuario findByUsername(String username);

	/**
	 * Bloquea al usuario en el sistema, impidiendo que se pueda loguear
	 * 
	 * @param entity usuario a bloquear
	 */
	void ejecutarBloqueo(Usuario entity);
	
	/**
	 * Desbloquea al usuario en el sistema, permitiendo que se pueda loguear
	 * 
	 * @param entity usuario a desbloquear
	 */
	void ejecutarDesbloqueo(Usuario entity);

	/**
	 * Verifica si el usuario tiene el acceso correspondiente
	 * 
	 * @param access acceso
	 * @return
	 */
	boolean userHasAccess(String access);

	/**
	 * Devuelve todos los usuarios que poseed el acceso
	 * 
	 * @param acceso acceso deseado
	 * @return
	 */
	Collection<Usuario> usuariosByAcceso(Acceso acceso);

}
