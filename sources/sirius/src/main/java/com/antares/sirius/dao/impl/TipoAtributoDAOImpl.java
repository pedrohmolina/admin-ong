package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.TipoAtributoDAO;
import com.antares.sirius.model.TipoAtributo;

/**
 * Implementacion de la interfaz TipoAtributoDAO.
 *
 * @version 1.0.0 Created 23/04/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class TipoAtributoDAOImpl extends GenericDAOImpl<TipoAtributo> implements TipoAtributoDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
