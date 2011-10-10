package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.PerfilDAO;
import com.antares.sirius.filter.PerfilFilter;
import com.antares.sirius.model.Perfil;

/**
 * Implementacion de la interfaz PerfilDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class PerfilDAOImpl extends BusinessEntityDAOImpl<Perfil> implements PerfilDAO {
	
	public Perfil findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(ilike("nombre", nombre, MatchMode.EXACT));
		return (Perfil)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Perfil> filter) {
		PerfilFilter entityFilter = (PerfilFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
