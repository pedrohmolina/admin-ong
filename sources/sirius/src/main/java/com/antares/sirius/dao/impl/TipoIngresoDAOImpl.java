package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.TipoIngresoDAO;
import com.antares.sirius.model.TipoIngreso;

/**
 * Implementacion de la interfaz TipoIngresoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class TipoIngresoDAOImpl extends GenericDAOImpl<TipoIngreso> implements TipoIngresoDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
