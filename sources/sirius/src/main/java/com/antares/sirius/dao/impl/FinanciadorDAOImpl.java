package com.antares.sirius.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.impl.BusinessEntityDAOImpl;
import com.antares.commons.filter.Filter;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.FinanciadorDAO;
import com.antares.sirius.filter.FinanciadorFilter;
import com.antares.sirius.model.Financiador;

/**
 * Implementacion de la interfaz FinanciadorDAO.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class FinanciadorDAOImpl extends BusinessEntityDAOImpl<Financiador> implements FinanciadorDAO {
	
	public Financiador findByNombre(String nombre) {
		Criteria crit = buildCriteria();
		crit.add(Restrictions.ilike("nombre", nombre, MatchMode.EXACT));
		return (Financiador)crit.uniqueResult();
	}

	@Override
	protected void addFilter(Criteria crit, Filter<Financiador> filter) {
		FinanciadorFilter entityFilter = (FinanciadorFilter)filter;
		if (Utils.isNotNullNorEmpty(entityFilter.getNombre())) {
			crit.add(Restrictions.ilike("nombre", entityFilter.getNombre(), MatchMode.ANYWHERE));
		}
		if (entityFilter.getTipoFinanciador() != null) {
			crit.add(Restrictions.eq("tipoFinanciador", entityFilter.getTipoFinanciador()));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getCuit())) {
			crit.add(Restrictions.eq("cuit", entityFilter.getCuit()));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getCbu())) {
			crit.add(Restrictions.eq("cbu", entityFilter.getCbu()));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getTelefono())) {
			crit.add(Restrictions.eq("telefono", entityFilter.getTelefono()));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getDireccion())) {
			crit.add(Restrictions.ilike("direccion", entityFilter.getDireccion(), MatchMode.EXACT));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getContacto())) {
			crit.add(Restrictions.ilike("contacto", entityFilter.getContacto(), MatchMode.ANYWHERE));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getCelular())) {
			crit.add(Restrictions.ilike("celular", entityFilter.getCelular(), MatchMode.EXACT));
		}
		if (Utils.isNotNullNorEmpty(entityFilter.getEmail())) {
			crit.add(Restrictions.ilike("email", entityFilter.getEmail(), MatchMode.EXACT));
		}
		if (entityFilter.getEstadoFinanciador() != null) {
			crit.add(Restrictions.eq("estadoFinanciador", entityFilter.getEstadoFinanciador()));
		}
	}

	@Override
	protected void addOrder(Criteria crit) {
		crit.addOrder(Order.asc("nombre"));
	}
}
