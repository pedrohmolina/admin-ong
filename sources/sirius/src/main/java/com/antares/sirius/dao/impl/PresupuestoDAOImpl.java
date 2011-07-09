package com.antares.sirius.dao.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.GenericDAOImpl;
import com.antares.sirius.dao.PresupuestoDAO;
import com.antares.sirius.model.Actividad;
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
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.createAlias("actividad", "actividad");
		crit.createAlias("actividad.meta", "meta");
		crit.createAlias("meta.objetivoEspecifico", "objetivoEspecifico");
		crit.createAlias("objetivoEspecifico.objetivoGeneral", "objetivoGeneral");
		crit.add(Restrictions.eq("objetivoGeneral.proyecto", proyecto));
		crit.addOrder(Order.asc("actividad"));
		crit.addOrder(Order.asc("rubro"));
		return (Collection<Presupuesto>)crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Presupuesto> findPresupuestoByProyecto(Proyecto proyecto) {
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("proyecto", proyecto));
		crit.addOrder(Order.asc("rubro"));
		return (Collection<Presupuesto>)crit.list();
	}

	@SuppressWarnings("unchecked")
	public Presupuesto findPresupuestoByActividadRubro(Actividad actividad, Rubro rubro) {
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("actividad", actividad));
		crit.add(Restrictions.eq("rubro", rubro));
		crit.addOrder(Order.asc("rubro"));
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
}
