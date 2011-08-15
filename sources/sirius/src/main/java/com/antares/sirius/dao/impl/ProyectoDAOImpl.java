package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.ProyectoDAO;
import com.antares.sirius.filter.ProyectoFilter;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Proyecto;

/**
 * Implementacion de la interfaz ProyectoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ProyectoDAOImpl extends BusinessEntityDAOImpl<Proyecto> implements ProyectoDAO {
	
	@SuppressWarnings("unchecked")
	public Collection<Proyecto> findAllByEstado(EstadoProyecto estadoProyecto) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.eq("estadoProyecto", estadoProyecto));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public Collection<Proyecto> findAllExceptEstados(EstadoProyecto ... estadoProyecto) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.not(Restrictions.in("estadoProyecto", estadoProyecto)));
		return crit.list();
	}

	public Proyecto findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(ilike("nombre", nombre, MatchMode.EXACT));
		return (Proyecto)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Proyecto> filter) {
		ProyectoFilter entityFilter = (ProyectoFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getFechaInicioDesde() != null) {
			crit.add(Restrictions.ge("fechaInicio", entityFilter.getFechaInicioDesde()));
		}
		if (entityFilter.getFechaInicioHasta() != null) {
			crit.add(Restrictions.le("fechaInicio", entityFilter.getFechaInicioHasta()));
		}
		if (entityFilter.getFinanciador() != null) {
			crit.add(Restrictions.eq("financiador", entityFilter.getFinanciador()));
		}
		if (entityFilter.getEstadoProyecto() != null) {
			crit.add(Restrictions.eq("estadoProyecto", entityFilter.getEstadoProyecto()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}

}
