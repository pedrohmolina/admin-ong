package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.sirius.dao.UsuarioDAO;
import com.antares.sirius.model.Usuario;

/**
 * Implementacion de la interfaz UsuarioDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class UsuarioDAOImpl extends BusinessEntityDAOImpl<Usuario> implements UsuarioDAO {
	
	public Usuario findByUsername(String username) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("username", username, MatchMode.EXACT));
		return (Usuario)crit.uniqueResult();
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("username"));
	}
}
