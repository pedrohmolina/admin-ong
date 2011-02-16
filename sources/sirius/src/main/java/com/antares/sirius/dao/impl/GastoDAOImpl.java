package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.sirius.dao.GastoDAO;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Gasto;

/**
 * Implementacion de la interfaz GastoDAO.
 *
 * @version 1.0.0 Created 16/02/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class GastoDAOImpl extends BusinessEntityDAOImpl<Gasto> implements GastoDAO {
	
	@Override
	protected void addFilter(Criteria crit, Filter<Gasto> filter) {
		GastoFilter entityFilter = (GastoFilter)filter;
		crit.add(Restrictions.eq("tipoGasto", entityFilter.getTipoGasto()));
		if (entityFilter.getFecha() != null) {
			crit.add(Restrictions.eq("fecha", entityFilter.getFecha()));
		}
		if (entityFilter.getRubro() != null) {
			crit.add(Restrictions.eq("rubro", entityFilter.getRubro()));
		}
		if (entityFilter.getOrigen() != null) {
			crit.add(Restrictions.eq("origen", entityFilter.getOrigen()));
		}
		if (entityFilter.getProveedor() != null) {
			crit.add(Restrictions.eq("proveedor", entityFilter.getProveedor()));
		}
		if (entityFilter.getPersona() != null) {
			crit.add(Restrictions.eq("persona", entityFilter.getPersona()));
		}
		if (entityFilter.getProyecto() != null) {
			crit.add(Restrictions.eq("proyecto", entityFilter.getProyecto()));
		}

		if (entityFilter.getActividad() != null) {
			crit.add(Restrictions.eq("actividad", entityFilter.getActividad()));
		} else if (entityFilter.getProyectoActividad() != null) {
			crit.createAlias("actividad", "actividad");
			crit.createAlias("actividad.meta", "meta");
			crit.createAlias("meta.objetivoEspecifico", "objetivoEspecifico");
			crit.createAlias("objetivoEspecifico.objetivoGeneral", "objetivoGeneral");
			crit.add(Restrictions.eq("objetivoGeneral.proyecto", entityFilter.getProyecto()));
		}
		
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.desc("fecha"));
	}
}
