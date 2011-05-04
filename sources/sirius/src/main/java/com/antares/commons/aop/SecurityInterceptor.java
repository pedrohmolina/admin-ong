package com.antares.commons.aop;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.antares.commons.predicate.PredicateFactory;
import com.antares.commons.predicate.PredicateFactoryHelper;
import com.antares.commons.predicate.PropertyType;
import com.antares.commons.predicate.RestrictionType;
import com.antares.commons.util.Utils;
import com.antares.sirius.dao.EntidadDAO;
import com.antares.sirius.dao.ReglaDAO;
import com.antares.sirius.model.BusinessObject;
import com.antares.sirius.model.Entidad;
import com.antares.sirius.model.EntidadReferenciada;
import com.antares.sirius.model.Regla;
import com.google.common.base.Predicate;

/**
 * Interceptor abstracto para el manejo de seguridad por valor
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public abstract class SecurityInterceptor implements MethodInterceptor {

	private ReglaDAO reglaDAO;
	private EntidadDAO entidadDAO;

	@Override
	public abstract Object invoke(MethodInvocation invocation) throws Throwable;

	/**
	 * Se encarga de filtrar el resultado devuelto por el método invocado, usando las reglas de seguridad por valor.
	 * 
	 * @param returnValue objeto devuelto por la invocacion original, que debe ser filtrado
	 * @param username nombre del usuario logueado
	 * @param entityName nombre de la entidad sobre la cual se aplican las reglas de seguridad por valor
	 * @return returnValue filtrado luego de pasar por las reglas de seguridad 
	 */
	@SuppressWarnings("unchecked")
	protected Object filterReturnValue(Object returnValue, String username, String entityName) {
		if (returnValue instanceof BusinessObject) { 
			if (filterObject((BusinessObject)returnValue, username, entityName)) {
				returnValue = null;
			}
		} else if (returnValue instanceof Collection) { 
			returnValue = filterCollection((Collection<BusinessObject>)returnValue, username, entityName);
		}
		return returnValue;
	}
	
	/**
	 * Determina si el BusinessObject pasador por parametro debe ser filtrado para el usuario, usando las reglas de seguridad 
	 * por valor.
	 * 
	 * @param targetObject objeto a determinar si se debe o no filtrar
	 * @param username nombre del usuario logueado
	 * @param entityName nombre de la entidad sobre la cual se aplican las reglas de seguridad por valor
	 * @return true si el objeto debe ser filtrado (no se debe mostrar al usuario) o false si el objeto no debe ser filtrado
	 */
	protected boolean filterObject(BusinessObject targetObject, String username, String entityName) {
		boolean doFilter = false;
		Entidad entidad = entidadDAO.findByNombreEntidad(entityName);
		if (entidad != null) {
			doFilter = filterObjectReferences(targetObject, entidad, username);
		}

		return doFilter;
	}
	
	/**
	 * Filtra la colleccion targetCollection pasada por parametro, eliminando los elementos que no deben visualizarse por el usuario,
	 * usando las reglas de seguridad por valor.
	 * 
	 * @param targetCollection colleccion a filtrar
	 * @param entityName nombre de la entidad sobre la cual se aplican las reglas de seguridad por valor
	 * @param username nombre del usuario logueado
	 * @return targetCollection filtrada, sin los elementos que el usuario no tiene permiso de ver
	 */
	protected Collection<BusinessObject> filterCollection(Collection<BusinessObject> targetCollection, String username, String entityName) {

		if (!targetCollection.isEmpty()) {
			Entidad entidad = entidadDAO.findByNombreEntidad(entityName);
			if (entidad != null) {
				Collection<BusinessObject> elementsToRemove = new HashSet<BusinessObject>();
				for (BusinessObject businessObject : targetCollection) {
					if (filterObjectReferences(businessObject, entidad, username)) {
						elementsToRemove.add(businessObject);
					}
				}
				targetCollection.removeAll(elementsToRemove);
			}
		}

		return targetCollection;
	}
	
	/**
	 * Revisa, de forma recursiva si el targetObject (o alguno de los objetos de los cuales depende) debe ser filtrado o no.
	 * 
	 * @param targetObject objeto a determinar si se debe o no filtrar.
	 * @param entidad entidad que representa a la clase del objeto sobre el cual evaluar las reglas de seguridad
	 * @param username nombre del usuario logueado
	 * @return true si el objeto debe ser filtrado (no se debe mostrar al usuario) o false si el objeto no debe ser filtrado
	 */
	@SuppressWarnings("unchecked")
	protected boolean filterObjectReferences(BusinessObject targetObject, Entidad entidad, String username) {
		
		// Reviso que a la entidad en cuestion se le puedan aplicar reglas de seguridad
		Collection<Regla> reglas = reglaDAO.findByUsernameAndEntidad(username, entidad);
		if (!reglas.isEmpty()) {
			for (Regla regla : reglas) {
				Object value = getPropertyValue(targetObject, regla.getAtributo().getNombreAtributo());
				if (value != null && createPredicate(regla).apply(value)) {
					return true;
				}
			}
		}
		
		// Reviso las reglas de seguridad de las entidades referenciadas usando una llamada recusiva
		for (EntidadReferenciada entidadReferenciada : entidad.getEntidadesReferenciadas()) {
			Entidad referencia = entidadReferenciada.getEntidadReferenciada();
			BusinessObject referencedObject = (BusinessObject)getPropertyValue(targetObject, entidadReferenciada.getNombreEntidad());

			// Si el objeto referenciado es distinto de null, realizo una llamada recursiva
			if (referencedObject != null) {
				if (filterObjectReferences(referencedObject, referencia, username)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Obtiene, el valor de una propiedad del businessObject.
	 *  
	 * @param businessObject objeto cuya propiedad se quiere obtener
	 * @param propertyName nombre de la propiedad
	 * @return
	 */
	protected Object getPropertyValue(BusinessObject businessObject, String propertyName) {
		Object rval = null;
		try {
			Class<?> clazz = businessObject.getClass();
			Method method = clazz.getMethod(Utils.getterName(propertyName), new Class[]{});
			rval = method.invoke(businessObject, new Object[]{});
		} catch (Exception e) {
			// TODO ver como manejar esto
			System.out.println("Problema al intentar acceder a la propiedad " + propertyName + " de la clase" + businessObject.getClass().getSimpleName());
			e.printStackTrace();
		}
		return rval;
	}

	/**
	 * Crea un predicado a partir de una regla de seguridad por valor.
	 * 
	 * @param regla regla de seguridad por valor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Predicate createPredicate(Regla regla) {
		PredicateFactory predicateBuilder = PredicateFactoryHelper.getPredicateFactory(PropertyType.findById(regla.getAtributo().getTipoAtributo().getId()));
		return predicateBuilder.createPredicate(regla.getAtributo().getNombreAtributo(), RestrictionType.findById(regla.getOperador().getId()), regla.getValor());
	}

	public void setReglaDAO(ReglaDAO reglaDAO) {
		this.reglaDAO = reglaDAO;
	}

	public void setEntidadDAO(EntidadDAO entidadDAO) {
		this.entidadDAO = entidadDAO;
	}
}
