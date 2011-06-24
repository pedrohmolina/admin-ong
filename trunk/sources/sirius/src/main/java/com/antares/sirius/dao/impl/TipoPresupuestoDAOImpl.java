package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.TipoPresupuestoDAO;
import com.antares.sirius.model.TipoPresupuesto;

/**
 * Implementacion de la interfaz TipoPresupuestoDAO.
 *
 * @version 1.0.0 Created 20/06/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class TipoPresupuestoDAOImpl extends GenericDAOImpl<TipoPresupuesto> implements TipoPresupuestoDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
