package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.ObjetivoEspecificoDAO;
import com.antares.sirius.filter.ObjetivoEspecificoFilter;
import com.antares.sirius.model.ObjetivoEspecifico;

/**
 * Implementacion de la interfaz ObjetivoEspecificoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ObjetivoEspecificoDAOImpl extends BusinessEntityDAOImpl<ObjetivoEspecifico> implements ObjetivoEspecificoDAO {
	
	public ObjetivoEspecifico findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("nombre", nombre, MatchMode.EXACT));
		return (ObjetivoEspecifico)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<ObjetivoEspecifico> filter) {
		ObjetivoEspecificoFilter entityFilter = (ObjetivoEspecificoFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getObjetivoGeneral() != null) {
			crit.add(Restrictions.eq("objetivoGeneral", entityFilter.getObjetivoGeneral()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
