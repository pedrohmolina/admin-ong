package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.sirius.dao.ReglaDAO;
import com.antares.sirius.model.Entidad;
import com.antares.sirius.model.Regla;

/**
 * Implementacion de la interfaz ReglaDAO.
 *
 * @version 1.0.0 Created 23/04/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ReglaDAOImpl extends BusinessEntityDAOImpl<Regla> implements ReglaDAO {
	
	@SuppressWarnings("unchecked")
	public Collection<Regla> findByUsernameAndEntidad(String username, Entidad entidad) {
		Criteria crit = getSession().createCriteria(persistentClass);
		addLogicConstraint(crit);
		crit.createAlias("usuario", "usuario");
		crit.createAlias("entidad", "entidad");
		crit.add(Restrictions.eq("usuario.username", username));
		crit.add(Restrictions.eq("entidad", entidad));
		addOrder(crit);
		return (Collection<Regla>)crit.list();
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.desc("usuario"));
	}

}
