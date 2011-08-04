package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.ProveedorDAO;
import com.antares.sirius.filter.ProveedorFilter;
import com.antares.sirius.model.Proveedor;

/**
 * Implementacion de la interfaz ProveedorDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class ProveedorDAOImpl extends BusinessEntityDAOImpl<Proveedor> implements ProveedorDAO {
	
	public Proveedor findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("nombre", nombre, MatchMode.EXACT));
		return (Proveedor)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Proveedor> filter) {
		ProveedorFilter entityFilter = (ProveedorFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getTipoProveedor() != null) {
			crit.add(Restrictions.eq("tipoProveedor", entityFilter.getTipoProveedor()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
