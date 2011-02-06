package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.PresupuestoDAO;
import com.antares.sirius.model.Presupuesto;

/**
 * Implementacion de la interfaz PresupuestoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class PresupuestoDAOImpl extends GenericDAOImpl<Presupuesto> implements PresupuestoDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		//TODO agregar orden
	}
}
