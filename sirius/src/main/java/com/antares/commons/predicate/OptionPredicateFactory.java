package com.antares.commons.predicate;

import com.antares.commons.exception.IncorrectRestrictionTypeException;
import com.antares.commons.util.Utils;
import com.google.common.base.Predicate;

/**
 * Factory Concreto que crea predicados para campos de tipo numerico
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public class OptionPredicateFactory extends PredicateFactory {

	public Predicate<?> createPredicate(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Predicate<Integer> predicate = null;
		Integer numericValue = Utils.parseInteger(value);
		switch(restrictionType) {
			case OPTION_EQUAL: predicate = setValues(new OptionEqualPredicate(), propertyName, numericValue); break; 
			case OPTION_NOT_EQUAL: predicate = setValues(new OptionNotEqualPredicate(), propertyName, numericValue); break;
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a numeric field");
		}
		return predicate;
	}

	private FilterPredicate<Integer> setValues(FilterPredicate<Integer> predicate, String propertyName, Integer expectedValue) {
		predicate.setPropertyName(propertyName);
		predicate.setExpectedValue(expectedValue);
		return predicate;
	}

	private static class OptionEqualPredicate extends FilterPredicate<Integer> {
		@Override
		public boolean apply(Integer fieldValue) {
			return expectedValue.equals(fieldValue);
		}
	}

	private static class OptionNotEqualPredicate extends FilterPredicate<Integer> {
		@Override
		public boolean apply(Integer fieldValue) {
			return !expectedValue.equals(fieldValue);
		}
	}

}
