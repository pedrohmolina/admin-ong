package com.antares.sirius.dao;

import java.util.Collection;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Acceso;
import com.antares.sirius.model.Usuario;

public interface UsuarioDAO extends BusinessEntityDAO<Usuario> {

	/**
	 * Devuelve un usuario a partir de su username (valor único)
	 * 
	 * @param username username a buscar
	 * @return
	 */
	Usuario findByUsername(String username);

	/**
	 * Devuelve todos los usuarios que poseed el acceso
	 * 
	 * @param acceso acceso deseado
	 * @return
	 */
	Collection<Usuario> usuariosByAcceso(Acceso acceso);

}
