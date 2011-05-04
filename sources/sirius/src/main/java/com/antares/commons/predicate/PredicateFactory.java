package com.antares.commons.predicate;

import com.antares.commons.exception.IncorrectRestrictionTypeException;
import com.google.common.base.Predicate;

/**
 * Abstract Factory que crea predicados
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public abstract class PredicateFactory {

	/**
	 * Crea un predicado a partir del nombre de una propiedad, un tipo de restriccion y un valor de la restriccion
	 * 
	 * @param propertyName nombre de la propiedad sobre la que se va a crear el predicado
	 * @param restrictionType tipo de restriccion
	 * @param restrictionValue valor de la restriccion
	 * @return predicado que evalua si una propiedad cumple o no una restriccion
	 * @throws IncorrectRestrictionTypeException si el tipo de restriccion es invalido
	 */
	public abstract Predicate<?> createPredicate(String propertyName, RestrictionType restrictionType, String restrictionValue) throws IncorrectRestrictionTypeException;

}
