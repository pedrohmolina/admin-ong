package com.antares.commons.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.antares.commons.criterion.IlikeExpression;
import com.antares.commons.criterion.LikeExpression;
import com.antares.commons.dao.GenericDAO;
import com.antares.sirius.model.PersistentObject;

/**
 * Implementacion gen�rica de la interfaz GenericDAO.
 *
 * @version 1.0.0 Created 27/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T extends PersistentObject> extends HibernateDaoSupport implements GenericDAO<T> {

	protected Class<T> persistentClass;

	public GenericDAOImpl() {
		this.persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * @see com.antares.sirius.dao.GenericDAO #findById(Serializable)
	 * {@inheritDoc}
	 */
	public T findById(Serializable id) {
		return getHibernateTemplate().get(persistentClass, id);
	}

	/**
	 * @see com.antares.sirius.dao.GenericDAO #findAll()
	 * {@inheritDoc}
	 */
	public Collection<T> findAll() {
		return getHibernateTemplate().loadAll(persistentClass);
	}

	/**
	 * @see com.antares.sirius.dao.GenericDAO #save(T)
	 * {@inheritDoc}
	 */
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * @see com.antares.sirius.dao.GenericDAO #update(T)
	 * {@inheritDoc}
	 */
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * @see com.antares.sirius.dao.GenericDAO #delete(T)
	 * {@inheritDoc}
	 */
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	protected Criteria buildCriteria() {
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		addOrder(crit);
		return crit;
	}

	protected void addOrder(Criteria crit) {
		
	}

	public static Criterion like(String propertyName, Object value) {     
		return new LikeExpression(propertyName, value); 
	}
	
	public static Criterion like(String propertyName, String value, MatchMode matchMode) {     
		return new LikeExpression(propertyName, value, matchMode); 
	}

	public static Criterion like(String propertyName, String value, Character escapeChar) {     
		return new LikeExpression(propertyName, value, escapeChar); 
	}

	public static Criterion ilike(String propertyName, Object value) {     
		return new IlikeExpression(propertyName, value); 
	}
	
	public static Criterion ilike(String propertyName, String value, MatchMode matchMode) {     
		return new IlikeExpression(propertyName, value, matchMode); 
	}
	
	public static Criterion ilike(String propertyName, String value, Character escapeChar) {     
		return new IlikeExpression(propertyName, value, escapeChar);
	}

}
