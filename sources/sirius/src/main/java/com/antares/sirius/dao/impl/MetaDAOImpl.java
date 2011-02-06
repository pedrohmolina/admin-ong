package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.MetaDAO;
import com.antares.sirius.filter.MetaFilter;
import com.antares.sirius.model.Meta;

/**
 * Implementacion de la interfaz MetaDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class MetaDAOImpl extends BusinessEntityDAOImpl<Meta> implements MetaDAO {
	
	public Meta findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("nombre", nombre, MatchMode.EXACT));
		return (Meta)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Meta> filter) {
		MetaFilter entityFilter = (MetaFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getObjetivoEspecifico() != null) {
			crit.add(Restrictions.eq("objetivoEspecifico", entityFilter.getObjetivoEspecifico()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
