package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.ProyectoDAO;
import com.antares.sirius.filter.ProyectoFilter;
import com.antares.sirius.model.Proyecto;

/**
 * Implementacion de la interfaz ProyectoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ProyectoDAOImpl extends BusinessEntityDAOImpl<Proyecto> implements ProyectoDAO {
	
	public Proyecto findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("nombre", nombre, MatchMode.EXACT));
		return (Proyecto)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Proyecto> filter) {
		ProyectoFilter entityFilter = (ProyectoFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getFechaInicio() != null) {
			crit.add(Restrictions.eq("fechaInicio", entityFilter.getFechaInicio()));
		}
		if (entityFilter.getFechaFin() != null) {
			crit.add(Restrictions.eq("fechaFin", entityFilter.getFechaFin()));
		}
		if (entityFilter.getResponsable() != null) {
			crit.add(Restrictions.eq("fechaFin", entityFilter.getResponsable()));
		}
		if (entityFilter.getFinanciador() != null) {
			crit.add(Restrictions.eq("fechaFin", entityFilter.getFinanciador()));
		}
		if (entityFilter.getAreaTematica() != null) {
			crit.add(Restrictions.eq("fechaFin", entityFilter.getAreaTematica()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}

}
