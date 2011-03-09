package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.TipoAgrupamientoDAO;
import com.antares.sirius.model.TipoAgrupamiento;

/**
 * Implementacion de la interfaz TipoAgrupamientoDAO.
 *
 * @version 1.0.0 Created 21/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class TipoAgrupamientoDAOImpl extends GenericDAOImpl<TipoAgrupamiento> implements TipoAgrupamientoDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("id"));
	}
}
