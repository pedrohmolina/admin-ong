package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.sirius.dao.IngresoDAO;
import com.antares.sirius.filter.IngresoFilter;
import com.antares.sirius.model.Ingreso;

/**
 * Implementacion de la interfaz IngresoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class IngresoDAOImpl extends BusinessEntityDAOImpl<Ingreso> implements IngresoDAO {
	
	@Override
	protected void addFilter(Criteria crit, Filter<Ingreso> filter) {
		IngresoFilter entityFilter = (IngresoFilter)filter;
		if (entityFilter.getTipoIngreso() != null) {
			crit.add(Restrictions.eq("tipoIngreso", entityFilter.getTipoIngreso()));
		}
		if (entityFilter.getFechaDesde() != null) {
			crit.add(Restrictions.ge("fecha", entityFilter.getFechaDesde()));
		}
		if (entityFilter.getFechaHasta() != null) {
			crit.add(Restrictions.le("fecha", entityFilter.getFechaHasta()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.desc("fecha"));
	}
}
