package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.UsuarioDAO;
import com.antares.sirius.model.Usuario;

/**
 * Implementacion de la interfaz UsuarioDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		//TODO agregar orden
	}
}
