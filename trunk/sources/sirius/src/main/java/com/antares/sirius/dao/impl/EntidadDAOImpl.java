package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.EntidadDAO;
import com.antares.sirius.model.Entidad;

/**
 * Implementacion de la interfaz EntidadDAO.
 *
 * @version 1.0.0 Created 23/04/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class EntidadDAOImpl extends GenericDAOImpl<Entidad> implements EntidadDAO {
	
	public Entidad findByNombreEntidad(String descripcion) {
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.add(ilike("nombreEntidad", descripcion, MatchMode.EXACT));
		return (Entidad)crit.uniqueResult();
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
