package com.antares.commons.dao.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.dao.BusinessEntityDAO;
import com.antares.commons.filter.Filter;
import com.antares.commons.restrictions.PropertyType;
import com.antares.commons.restrictions.RestrictionBuilder;
import com.antares.commons.restrictions.RestrictionBuilderFactory;
import com.antares.commons.restrictions.RestrictionType;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.EntidadDAO;
import com.antares.sirius.dao.ReglaDAO;
import com.antares.sirius.model.BusinessObject;
import com.antares.sirius.model.Entidad;
import com.antares.sirius.model.EntidadReferenciada;
import com.antares.sirius.model.Regla;

/**
 * Implementacion genérica de la interfaz GenericDAO.
 *
 * @version 1.0.0 Created 27/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
@SuppressWarnings("unchecked")
public abstract class BusinessEntityDAOImpl<T extends BusinessObject> extends GenericDAOImpl<T> implements BusinessEntityDAO<T> {

	private ReglaDAO reglaDAO;
	private EntidadDAO entidadDAO;
	private Boolean usarSeguridadPorValor = Boolean.FALSE;

	/**
	 * @see com.antares.sirius.service.GenericDAO #findByFilter(Filter<T>)
	 * {@inheritDoc}
	 */
	public Collection<T> findByFilter(Filter<T> filter) {
		Criteria crit = buildCriteria();
		addFilter(crit, filter);
		return crit.list();
	}

	/**
	 * @see com.antares.sirius.service.GenericDAO #findAll()
	 * {@inheritDoc}
	 */
	public Collection<T> findAll() {
		return buildCriteria().list();
	}

	protected Criteria buildCriteria() {
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		addLogicConstraint(crit);
		addSecurityRestrictions(crit);
		addOrder(crit);
		return crit;
	}

	/**
	 * @see com.antares.sirius.service.GenericDAO #delete(T)
	 * {@inheritDoc}
	 */
	public void delete(T entity) {
		entity.setActivo(FALSE);
		getHibernateTemplate().update(entity);
	}

	protected void addLogicConstraint(Criteria crit) {
		if (BusinessObject.class.isAssignableFrom(persistentClass)) {
			crit.add(Restrictions.eq("activo", TRUE));
		}
	}

	protected void addSecurityRestrictions(Criteria crit) {
		Authentication authentication = Utils.getAuthentication();
		if (authentication != null) {
			String username = authentication.getName();

			// Reviso que el usuario este logueado y que la seguridad por valor este activada
			if (!authentication.getPrincipal().equals("anonymous") && this.usarSeguridadPorValor) {	

				// Reviso que a la entidad en cuestion se le puedan aplicar reglas de seguridad
				Entidad entidad = entidadDAO.findByNombreEntidad(persistentClass.getSimpleName());
				if (entidad != null) {
					Collection<Regla> reglas = reglaDAO.findByUsernameAndEntidad(username, entidad);
	
					// Cargo las reglas correspondientes a la entidad
					for (Regla regla : reglas) {
						crit.add(Restrictions.not(createRestriction(regla)));
					}
	
					// Cargo las reglas correspondientes a las entidades referenciadas
					Set<String> references = new HashSet<String>();
					addReferencedRestrictions(username, entidad, references, crit);
				}
			}
		}
	}

	protected boolean addReferencedRestrictions(String username, Entidad entidad, Set<String> references, Criteria crit) {
		return addReferencedRestrictions(username, entidad, references, crit, null);
	}

	protected boolean addReferencedRestrictions(String username, Entidad entidad, Set<String> references, Criteria crit, String path) {
		boolean added = false;
		String prefix = path == null ? "" : path + ".";

		for (EntidadReferenciada entidadReferenciada : entidad.getEntidadesReferenciadas()) {
			if (!entidadReferenciada.getOpcional()) { // TODO sacar, por ahora no soporta referencias opcionales
				Entidad referencia = entidadReferenciada.getEntidadReferenciada();
				boolean addedReferences = false;
	
				// Para evitar referencias circulares
				if (!references.contains(entidadReferenciada.getNombreEntidad())) {
					references.add(entidadReferenciada.getNombreEntidad());
	
					Collection<Regla> reglas = reglaDAO.findByUsernameAndEntidad(username, referencia);
					if (!reglas.isEmpty()) {
						addedReferences = true;
						for (Regla regla : reglas) {
							crit.add(Restrictions.not(createRestriction(regla, entidadReferenciada.getNombreEntidad())));
						}
					}
	
					boolean addedMoreReferences = addReferencedRestrictions(username, referencia, references, crit, entidadReferenciada.getNombreEntidad());
	
					addedReferences = addedReferences || addedMoreReferences; 
					if (addedReferences) {
						added = true;
//						if (!entidadReferenciada.getOpcional()) {
							crit.createAlias(prefix + entidadReferenciada.getNombreEntidad(), entidadReferenciada.getNombreEntidad());
//						} else {
// 							crit.createAlias(prefix + entidadReferenciada.getNombreEntidad(), entidadReferenciada.getNombreEntidad(), Criteria.LEFT_JOIN);
//						}
					}
				}
			}
		}
		return added;
	}

	private void createAliases(Criteria crit, Map<String, String> aliases) {
		for (String key : aliases.keySet()) {
			crit.createAlias(key, aliases.get(key)); //TODO ver que hacer para los optionals
		}
	}
	
	protected Criterion createRestriction(Regla regla) {
		return createRestriction(regla, null);
	}

	protected Criterion createRestriction(Regla regla, String path) {
		String prefix = path == null ? "" : path + ".";
		RestrictionBuilder restrictionBuilder = RestrictionBuilderFactory.getRestrictionBuilder(PropertyType.findById(regla.getAtributo().getTipoAtributo().getId()));
		return restrictionBuilder.buildRestriction(prefix + regla.getAtributo().getNombreAtributo(), RestrictionType.findById(regla.getOperador().getId()), regla.getValor());
	}

	protected void addFilter(Criteria crit, Filter<T> filter) {
		
	}

	public void setReglaDAO(ReglaDAO reglaDAO) {
		this.reglaDAO = reglaDAO;
	}

	public void setUsarSeguridadPorValor(Boolean usarSeguridadPorValor) {
		this.usarSeguridadPorValor = usarSeguridadPorValor;
	}

	public void setEntidadDAO(EntidadDAO entidadDAO) {
		this.entidadDAO = entidadDAO;
	}
}
