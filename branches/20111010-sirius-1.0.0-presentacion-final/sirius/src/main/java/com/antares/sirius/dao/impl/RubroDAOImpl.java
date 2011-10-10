package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.RubroDAO;
import com.antares.sirius.filter.RubroFilter;
import com.antares.sirius.model.Rubro;

/**
 * Implementacion de la interfaz RubroDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class RubroDAOImpl extends BusinessEntityDAOImpl<Rubro> implements RubroDAO {

	@SuppressWarnings("unchecked")
	public Collection<Rubro> findByIds(Integer[] ids) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.in("id", ids));
		return crit.list();
	}

	public Rubro findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(ilike("nombre", nombre, MatchMode.EXACT));
		return (Rubro)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<Rubro> findPrimerNivel() {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.isNull("rubroPadre"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public Collection<Rubro> findHijos(Integer id) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.eq("rubroPadre.id", id));
		return crit.list();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Rubro> filter) {
		RubroFilter entityFilter = (RubroFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
