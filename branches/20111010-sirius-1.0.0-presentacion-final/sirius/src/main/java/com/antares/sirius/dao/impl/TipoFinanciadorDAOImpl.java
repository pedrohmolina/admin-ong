package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.TipoFinanciadorDAO;
import com.antares.sirius.model.TipoFinanciador;

/**
 * Implementacion de la interfaz TipoFinanciadorDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class TipoFinanciadorDAOImpl extends GenericDAOImpl<TipoFinanciador> implements TipoFinanciadorDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
