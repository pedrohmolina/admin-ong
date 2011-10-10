package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.sirius.dao.NotificacionDAO;
import com.antares.sirius.model.Notificacion;

/**
 * Implementacion de la interfaz NotificacionDAO.
 *
 * @version 1.0.0 Created 14/08/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class NotificacionDAOImpl extends BusinessEntityDAOImpl<Notificacion> implements NotificacionDAO {
	
	@SuppressWarnings("unchecked")
	public Collection<Notificacion> findByUsername(String username) {
		return findByUsername(username, null);
	}

	@SuppressWarnings("unchecked")
	public Collection<Notificacion> findByUsername(String username, Boolean leidas) {
		Criteria crit = buildCriteria();
		crit.createAlias("usuario", "usuario");
		crit.add(Restrictions.like("usuario.username", username, MatchMode.EXACT));
		if (leidas != null) {
			crit.add(Restrictions.eq("leida", leidas));
		}
		return (Collection<Notificacion>)crit.list();
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.desc("fecha"));
	}
}
