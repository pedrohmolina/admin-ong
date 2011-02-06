package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.EstadoProyectoDAO;
import com.antares.sirius.model.EstadoProyecto;

/**
 * Implementacion de la interfaz EstadoProyectoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class EstadoProyectoDAOImpl extends GenericDAOImpl<EstadoProyecto> implements EstadoProyectoDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
