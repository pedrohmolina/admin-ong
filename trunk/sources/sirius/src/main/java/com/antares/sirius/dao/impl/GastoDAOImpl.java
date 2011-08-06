package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.sirius.dao.GastoDAO;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.filter.GastoFilter;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Gasto;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;

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
		if (entityFilter.getFechaDesde() != null) {
			crit.add(Restrictions.ge("fecha", entityFilter.getFechaDesde()));
		}
		if (entityFilter.getFechaHasta() != null) {
			crit.add(Restrictions.le("fecha", entityFilter.getFechaHasta()));
		}
		if (entityFilter.getRubro() != null) {
			crit.add(Restrictions.eq("rubro", entityFilter.getRubro()));
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
			crit.add(Restrictions.eq("objetivoGeneral.proyecto", entityFilter.getProyectoActividad()));
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Gasto> findAllByProyecto(Proyecto proyecto) {
		Criteria crit = buildCriteria();
		crit.createAlias("actividad", "actividad");
		crit.createAlias("actividad.meta", "meta");
		crit.createAlias("meta.objetivoEspecifico", "objetivoEspecifico");
		crit.createAlias("objetivoEspecifico.objetivoGeneral", "objetivoGeneral");

		Disjunction disjuntion = Restrictions.disjunction();
		disjuntion.add(Restrictions.eq("objetivoGeneral.proyecto", proyecto));
		disjuntion.add(Restrictions.eq("proyecto", proyecto));
		crit.add(disjuntion);
		
		return (Collection<Gasto>)crit.list();
	}

	@Override
	public boolean existenGastosProyecto(Proyecto proyecto) {
		return findAllByProyecto(proyecto).size() > 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<MontoDTO> obtainMontosByProyectoAndRubro(Proyecto proyecto, Rubro[] rubros) {
		Criteria crit = buildCriteria();
		crit.createAlias("actividad", "actividad");
		crit.createAlias("actividad.meta", "meta");
		crit.createAlias("meta.objetivoEspecifico", "objetivoEspecifico");
		crit.createAlias("objetivoEspecifico.objetivoGeneral", "objetivoGeneral");
		crit.add(Restrictions.eq("objetivoGeneral.proyecto", proyecto));
		crit.add(Restrictions.in("rubro", rubros));

		ProjectionList projection = Projections.projectionList();
		projection.add(Projections.property("objetivoGeneral.id").as("id"));
		projection.add(Projections.property("rubro.id").as("idRubro"));
		projection.add(Projections.sum("importe").as("montoGastado"));
		projection.add(Projections.groupProperty("objetivoEspecifico.objetivoGeneral"));
		projection.add(Projections.groupProperty("rubro"));
		crit.setProjection(projection);
		crit.setResultTransformer(Transformers.aliasToBean(MontoDTO.class));

		return (Collection<MontoDTO>)crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<MontoDTO> obtainMontosByObjetivoGeneralAndRubro(ObjetivoGeneral objetivoGeneral, Rubro[] rubros) {
		Criteria crit = buildCriteria();
		crit.createAlias("actividad", "actividad");
		crit.createAlias("actividad.meta", "meta");
		crit.createAlias("meta.objetivoEspecifico", "objetivoEspecifico");
		crit.add(Restrictions.eq("objetivoEspecifico.objetivoGeneral", objetivoGeneral));
		crit.add(Restrictions.in("rubro", rubros));

		ProjectionList projection = Projections.projectionList();
		projection.add(Projections.property("objetivoEspecifico.id").as("id"));
		projection.add(Projections.property("rubro.id").as("idRubro"));
		projection.add(Projections.sum("importe").as("montoGastado"));
		projection.add(Projections.groupProperty("meta.objetivoEspecifico"));
		projection.add(Projections.groupProperty("rubro"));
		crit.setProjection(projection);
		crit.setResultTransformer(Transformers.aliasToBean(MontoDTO.class));

		return (Collection<MontoDTO>)crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<MontoDTO> obtainMontosByObjetivoEspecificoAndRubro(ObjetivoEspecifico objetivoEspecifico, Rubro[] rubros) {
		Criteria crit = buildCriteria();
		crit.createAlias("actividad", "actividad");
		crit.createAlias("actividad.meta", "meta");
		crit.add(Restrictions.eq("meta.objetivoEspecifico", objetivoEspecifico));
		crit.add(Restrictions.in("rubro", rubros));

		ProjectionList projection = Projections.projectionList();
		projection.add(Projections.property("meta.id").as("id"));
		projection.add(Projections.property("rubro.id").as("idRubro"));
		projection.add(Projections.sum("importe").as("montoGastado"));
		projection.add(Projections.groupProperty("actividad.meta"));
		projection.add(Projections.groupProperty("rubro"));
		crit.setProjection(projection);
		crit.setResultTransformer(Transformers.aliasToBean(MontoDTO.class));

		return (Collection<MontoDTO>)crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<MontoDTO> obtainMontosByMetaAndRubro(Meta meta, Rubro[] rubros) {
		Criteria crit = buildCriteria();
		crit.createAlias("actividad", "actividad");
		crit.add(Restrictions.eq("actividad.meta", meta));
		crit.add(Restrictions.in("rubro", rubros));

		ProjectionList projection = Projections.projectionList();
		projection.add(Projections.property("actividad.id").as("id"));
		projection.add(Projections.property("rubro.id").as("idRubro"));
		projection.add(Projections.sum("importe").as("montoGastado"));
		projection.add(Projections.groupProperty("actividad"));
		projection.add(Projections.groupProperty("rubro"));
		crit.setProjection(projection);
		crit.setResultTransformer(Transformers.aliasToBean(MontoDTO.class));

		return (Collection<MontoDTO>)crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<MontoDTO> obtainMontosByActividadAndRubro(Actividad actividad, Rubro[] rubros) {
		Criteria crit = buildCriteria();
		crit.createAlias("actividad", "actividad");
		crit.add(Restrictions.eq("actividad", actividad));
		crit.add(Restrictions.in("rubro", rubros));

		ProjectionList projection = Projections.projectionList();
		projection.add(Projections.property("actividad.id").as("id"));
		projection.add(Projections.property("rubro.id").as("idRubro"));
		projection.add(Projections.sum("importe").as("montoGastado"));
		projection.add(Projections.groupProperty("actividad"));
		projection.add(Projections.groupProperty("rubro"));
		crit.setProjection(projection);
		crit.setResultTransformer(Transformers.aliasToBean(MontoDTO.class));

		return (Collection<MontoDTO>)crit.list();
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.desc("fecha"));
	}
}
