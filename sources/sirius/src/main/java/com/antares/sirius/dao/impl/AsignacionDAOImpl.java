package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.sirius.dao.AsignacionDAO;
import com.antares.sirius.filter.AsignacionFilter;
import com.antares.sirius.model.Asignacion;

/**
 * Implementacion de la interfaz AsignacionDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class AsignacionDAOImpl extends BusinessEntityDAOImpl<Asignacion> implements AsignacionDAO {
	
	@Override
	protected void addFilter(Criteria crit, Filter<Asignacion> filter) {
		AsignacionFilter entityFilter = (AsignacionFilter)filter;
		if (entityFilter.getActividad() != null) {
			crit.add(Restrictions.eq("actividad", entityFilter.getActividad()));
		}
		if (entityFilter.getPersona() != null) {
			crit.add(Restrictions.eq("persona", entityFilter.getPersona()));
		}
		if (entityFilter.getTipoAsignacion() != null) {
			crit.add(Restrictions.eq("tipoAsignacion", entityFilter.getTipoAsignacion()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("actividad"));
		crit.addOrder(Order.asc("tipoAsignacion"));
		crit.addOrder(Order.asc("persona"));
	}
}
