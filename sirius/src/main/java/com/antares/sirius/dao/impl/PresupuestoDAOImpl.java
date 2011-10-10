package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.PresupuestoDAO;
import com.antares.sirius.dto.MontoDTO;
import com.antares.sirius.model.Actividad;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;
import com.antares.sirius.model.Presupuesto;
import com.antares.sirius.model.Proyecto;
import com.antares.sirius.model.Rubro;

/**
 * Implementacion de la interfaz PresupuestoDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class PresupuestoDAOImpl extends GenericDAOImpl<Presupuesto> implements PresupuestoDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Presupuesto> findPresupuestoActividadByProyecto(Proyecto proyecto) {
		Criteria crit = buildCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.createAlias("actividad", "actividad");
		crit.createAlias("actividad.meta", "meta");
		crit.createAlias("meta.objetivoEspecifico", "objetivoEspecifico");
		crit.createAlias("objetivoEspecifico.objetivoGeneral", "objetivoGeneral");
		crit.add(Restrictions.eq("objetivoGeneral.proyecto", proyecto));
		return (Collection<Presupuesto>)crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Presupuesto> findPresupuestoByProyecto(Proyecto proyecto) {
		Criteria crit = buildCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("proyecto", proyecto));
		return (Collection<Presupuesto>)crit.list();
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
		projection.add(Projections.sum("monto").as("montoPresupuestado"));
		projection.add(Projections.sum("monto").as("montoDif"));
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
		projection.add(Projections.sum("monto").as("montoPresupuestado"));
		projection.add(Projections.sum("monto").as("montoDif"));
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
		projection.add(Projections.sum("monto").as("montoPresupuestado"));
		projection.add(Projections.sum("monto").as("montoDif"));
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
		projection.add(Projections.sum("monto").as("montoPresupuestado"));
		projection.add(Projections.sum("monto").as("montoDif"));
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
		projection.add(Projections.sum("monto").as("montoPresupuestado"));
		projection.add(Projections.sum("monto").as("montoDif"));
		projection.add(Projections.groupProperty("actividad"));
		projection.add(Projections.groupProperty("rubro"));
		crit.setProjection(projection);
		crit.setResultTransformer(Transformers.aliasToBean(MontoDTO.class));

		return (Collection<MontoDTO>)crit.list();
	}

	public Presupuesto findPresupuestoProyecto(Proyecto proyecto, Rubro rubro) {
		Criteria crit = buildCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("proyecto", proyecto));
		if (rubro != null) {
			crit.add(Restrictions.eq("rubro", rubro));
		}
		return (Presupuesto)crit.uniqueResult();
	}

	public Presupuesto findPresupuestoActividad(Actividad actividad, Rubro rubro) {
		Criteria crit = buildCriteria();
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("actividad", actividad));
		if (rubro != null) {
			crit.add(Restrictions.eq("rubro", rubro));
		}
		return (Presupuesto)crit.uniqueResult();
	}

	@Override
	public void save(Presupuesto presupuesto) {
		if (presupuesto.getId() != null) {
			getSession().merge(presupuesto);
		} else {
			getSession().save(presupuesto);
		}
	}

	protected Criteria buildCriteria() {
		Criteria crit = getSession().createCriteria(persistentClass);
		addOrder(crit);
		return crit;
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("rubro"));
	}

}
