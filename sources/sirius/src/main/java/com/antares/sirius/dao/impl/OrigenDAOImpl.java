package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.OrigenDAO;
import com.antares.sirius.model.Origen;

/**
 * Implementacion de la interfaz OrigenDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class OrigenDAOImpl extends GenericDAOImpl<Origen> implements OrigenDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
