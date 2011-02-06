package com.antares.commons.dao.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Collection;

import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.commons.filter.Filter;
import com.antares.sirius.model.BusinessObject;

/**
 * Implementacion genérica de la interfaz GenericDAO.
 *
 * @version 1.0.0 Created 27/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("unchecked")
public abstract class BusinessEntityDAOImpl<T extends BusinessObject> extends GenericDAOImpl<T> implements BusinessEntityDAO<T> {

	/**
	 * @see com.antares.sirius.service.GenericDAO #findByFilter(Filter<T>)
	 * {@inheritDoc}
	 */
	public Collection<T> findByFilter(Filter<T> filter) {
		Criteria crit = buildCriteria();
		addFilter(crit, filter);
		return crit.list();
	}

	/**
	 * @see com.antares.sirius.service.GenericDAO #findAll()
	 * {@inheritDoc}
	 */
	public Collection<T> findAll() {
		return buildCriteria().list();
	}

	protected Criteria buildCriteria() {
		Criteria crit = getSession().createCriteria(persistentClass);
		addLogicConstraint(crit);
		addOrder(crit);
		return crit;
	}

	/**
	 * @see com.antares.sirius.service.GenericDAO #delete(T)
	 * {@inheritDoc}
	 */
	public void delete(T entity) {
		entity.setActivo(FALSE);
		getHibernateTemplate().update(entity);
	}

	protected void addLogicConstraint(Criteria crit) {
		if (BusinessObject.class.isAssignableFrom(persistentClass)) {
			crit.add(Restrictions.eq("activo", TRUE));
		}
	}

	protected void addFilter(Criteria crit, Filter<T> filter) {
		
	}
}
