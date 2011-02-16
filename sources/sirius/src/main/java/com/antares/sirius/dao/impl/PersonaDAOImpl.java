package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.PersonaDAO;
import com.antares.sirius.filter.PersonaFilter;
import com.antares.sirius.model.Persona;

/**
 * Implementacion de la interfaz PersonaDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class PersonaDAOImpl extends BusinessEntityDAOImpl<Persona> implements PersonaDAO {
	
	@Override
	protected void addFilter(Criteria crit, Filter<Persona> filter) {
		PersonaFilter entityFilter = (PersonaFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getApellido())) {
			crit.add(Restrictions.ilike("apellido", entityFilter.getApellido(), MatchMode.ANYWHERE));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getCuit())) {
			crit.add(Restrictions.ilike("cuit", entityFilter.getCuit(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getNumeroDocumento() != null) {
			crit.add(Restrictions.eq("numeroDocumento", entityFilter.getNumeroDocumento()));
		}
		if (entityFilter.getRelacionContractual() != null) {
			crit.add(Restrictions.eq("relacionContractual", entityFilter.getRelacionContractual()));
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Persona> findAllOthers(Integer id) {
		Criteria crit = getSession().createCriteria(persistentClass);
		if (id != null) {
			crit.add(Restrictions.ne("id", id));
		}
		addLogicConstraint(crit);
		addOrder(crit);
		return crit.list();
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("apellido"));
	}

}
