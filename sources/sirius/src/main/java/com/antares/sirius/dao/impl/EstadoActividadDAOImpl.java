package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.EstadoActividadDAO;
import com.antares.sirius.model.EstadoActividad;

/**
 * Implementacion de la interfaz EstadoActividadDAO.
 *
 * @version 1.0.0 Created 12/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class EstadoActividadDAOImpl extends GenericDAOImpl<EstadoActividad> implements EstadoActividadDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("descripcion"));
	}
}
