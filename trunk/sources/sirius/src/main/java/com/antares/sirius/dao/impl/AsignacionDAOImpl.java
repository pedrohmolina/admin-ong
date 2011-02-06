package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.AsignacionDAO;
import com.antares.sirius.model.Asignacion;

/**
 * Implementacion de la interfaz AsignacionDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class AsignacionDAOImpl extends GenericDAOImpl<Asignacion> implements AsignacionDAO {
	
	@Override
	protected void addOrder(Criteria crit) {
		//TODO agregar orden
	}
}
