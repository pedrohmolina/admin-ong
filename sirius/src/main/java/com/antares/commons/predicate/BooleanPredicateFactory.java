package com.antares.commons.predicate;

import com.antares.commons.exception.IncorrectRestrictionTypeException;
import com.google.common.base.Predicate;

/**
 * Factory Concreto que crea predicados para campos de tipo booleano
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public class BooleanPredicateFactory extends PredicateFactory {

	public Predicate<?> createPredicate(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Predicate<Boolean> predicate = null;
		switch(restrictionType) {
			case BOOLEAN_TRUE: predicate = setValues(new BooleanTruePredicate(), propertyName); break; 
			case BOOLEAN_FALSE: predicate = setValues(new BooleanFalsePredicate(), propertyName); break;
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a boolean field");
		}
		return predicate;
	}

	private FilterPredicate<Boolean> setValues(FilterPredicate<Boolean> predicate, String propertyName) {
		predicate.setPropertyName(propertyName);
		return predicate;
	}

	private static class BooleanTruePredicate extends FilterPredicate<Boolean> {
		@Override
		public boolean apply(Boolean fieldValue) {
			return Boolean.TRUE.equals(fieldValue);
		}
	}

	private static class BooleanFalsePredicate extends FilterPredicate<Boolean> {
		@Override
		public boolean apply(Boolean fieldValue) {
			return Boolean.FALSE.equals(fieldValue);
		}
	}
}
