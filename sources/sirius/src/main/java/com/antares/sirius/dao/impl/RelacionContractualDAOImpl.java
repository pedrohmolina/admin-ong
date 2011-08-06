package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.RelacionContractualDAO;
import com.antares.sirius.filter.RelacionContractualFilter;
import com.antares.sirius.model.RelacionContractual;

/**
 * Implementacion de la interfaz RelacionContractualDAO.
 *
 * @version 1.0.0 Created 22/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class RelacionContractualDAOImpl extends BusinessEntityDAOImpl<RelacionContractual> implements RelacionContractualDAO {
	
	public RelacionContractual findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(ilike("nombre", nombre, MatchMode.EXACT));
		return (RelacionContractual)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<RelacionContractual> filter) {
		RelacionContractualFilter entityFilter = (RelacionContractualFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
