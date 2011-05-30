package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.ArchivoDAO;
import com.antares.sirius.model.Archivo;

/**
 * Implementacion de la interfaz ArchivoDAO.
 *
 * @version 1.0.0 Created 28/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ArchivoDAOImpl extends GenericDAOImpl<Archivo> implements ArchivoDAO {
	
	public Archivo findByHash(String hash) {
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.add(Restrictions.like("hash", hash, MatchMode.EXACT));
		return (Archivo)crit.uniqueResult();
	}

	@Override
	protected void addOrder(Criteria crit) {
	}
}
