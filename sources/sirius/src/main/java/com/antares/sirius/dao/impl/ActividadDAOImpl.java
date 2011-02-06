package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.ActividadDAO;
import com.antares.sirius.filter.ActividadFilter;
import com.antares.sirius.model.Actividad;

/**
 * Implementacion de la interfaz ActividadDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ActividadDAOImpl extends BusinessEntityDAOImpl<Actividad> implements ActividadDAO {
	
	public Actividad findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("nombre", nombre, MatchMode.EXACT));
		return (Actividad)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Actividad> filter) {
		ActividadFilter entityFilter = (ActividadFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getMeta() != null) {
			crit.add(Restrictions.eq("meta", entityFilter.getMeta()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
