package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.ObjetivoGeneralDAO;
import com.antares.sirius.filter.ObjetivoGeneralFilter;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;

/**
 * Implementacion de la interfaz ObjetivoGeneralDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ObjetivoGeneralDAOImpl extends BusinessEntityDAOImpl<ObjetivoGeneral> implements ObjetivoGeneralDAO {
	
	public ObjetivoGeneral findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("nombre", nombre, MatchMode.EXACT));
		return (ObjetivoGeneral)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<ObjetivoGeneral> findAllByProyecto(Proyecto proyecto) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.eq("proyecto", proyecto));
		return crit.list();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<ObjetivoGeneral> filter) {
		ObjetivoGeneralFilter entityFilter = (ObjetivoGeneralFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getProyecto() != null) {
			crit.add(Restrictions.eq("proyecto", entityFilter.getProyecto()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
