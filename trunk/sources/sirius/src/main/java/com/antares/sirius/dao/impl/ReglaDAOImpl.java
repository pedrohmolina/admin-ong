package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.sirius.dao.ReglaDAO;
import com.antares.sirius.model.Regla;

/**
 * Implementacion de la interfaz ReglaDAO.
 *
 * @version 1.0.0 Created 23/04/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ReglaDAOImpl extends BusinessEntityDAOImpl<Regla> implements ReglaDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.desc("usuario"));
	}

}
