package com.antares.sirius.dao;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.sirius.model.Usuario;

public interface UsuarioDAO extends BusinessEntityDAO<Usuario> {

	/**
	 * Devuelve un usuario a partir de su username (valor único)
	 * 
	 * @param username username a buscar
	 * @return
	 */
	Usuario findByUsername(String username);

}
