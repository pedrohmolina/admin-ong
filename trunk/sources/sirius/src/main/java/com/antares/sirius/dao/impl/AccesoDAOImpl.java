package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.AccesoDAO;
import com.antares.sirius.model.Acceso;

/**
 * Implementacion de la interfaz AccesoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class AccesoDAOImpl extends GenericDAOImpl<Acceso> implements AccesoDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("recurso"));
		crit.addOrder(Order.asc("accion"));
		//TODO revisar que esto funcione y si no deberia usar recurso.nombre
	}
}
