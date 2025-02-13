package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.MetaDAO;
import com.antares.sirius.filter.MetaFilter;
import com.antares.sirius.model.EstadoProyecto;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.Proyecto;

/**
 * Implementacion de la interfaz MetaDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class MetaDAOImpl extends BusinessEntityDAOImpl<Meta> implements MetaDAO {
	
	public Meta findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(ilike("nombre", nombre, MatchMode.EXACT));
		return (Meta)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<Meta> findAllByProyecto(Proyecto proyecto) {
		Criteria crit = buildCriteria();
		crit.createAlias("objetivoEspecifico", "objetivoEspecifico");
		crit.createAlias("objetivoEspecifico.objetivoGeneral", "objetivoGeneral");
		crit.add(Restrictions.eq("objetivoGeneral.proyecto", proyecto));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public Collection<Meta> findAllExceptEstados(EstadoProyecto ... estadoProyecto) {
		Criteria crit = buildCriteria();
		crit.createAlias("objetivoEspecifico", "objetivoEspecifico");
		crit.createAlias("objetivoEspecifico.objetivoGeneral", "objetivoGeneral");
		crit.createAlias("objetivoGeneral.proyecto", "proyecto");
		crit.add(Restrictions.not(Restrictions.in("proyecto.estadoProyecto", estadoProyecto)));
		return crit.list();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Meta> filter) {
		MetaFilter entityFilter = (MetaFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getObjetivoEspecifico() != null) {
			crit.add(Restrictions.eq("objetivoEspecifico", entityFilter.getObjetivoEspecifico()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
