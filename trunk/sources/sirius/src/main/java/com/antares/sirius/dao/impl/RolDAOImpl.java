package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.RolDAO;
import com.antares.sirius.filter.RolFilter;
import com.antares.sirius.model.Rol;

/**
 * Implementacion de la interfaz RolDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class RolDAOImpl extends BusinessEntityDAOImpl<Rol> implements RolDAO {
	
	public Rol findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(ilike("nombre", nombre, MatchMode.EXACT));
		return (Rol)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Rol> filter) {
		RolFilter entityFilter = (RolFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
