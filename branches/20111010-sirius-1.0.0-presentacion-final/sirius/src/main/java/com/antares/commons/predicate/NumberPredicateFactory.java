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
public class NumberPredicateFactory extends PredicateFactory {

	public Predicate<?> createPredicate(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Predicate<Number> predicate = null;
		Number numericValue = Utils.parseDouble(value);
		switch(restrictionType) {
			case NUMERIC_EQUAL: predicate = setValues(new NumberEqualPredicate(), propertyName, numericValue); break; 
			case NUMERIC_NOT_EQUAL: predicate = setValues(new NumberNotEqualPredicate(), propertyName, numericValue); break;
			case NUMERIC_LESS_THAN: predicate = setValues(new NumberLessThanPredicate(), propertyName, numericValue); break;
			case NUMERIC_LESS_OR_EQUAL: predicate = setValues(new NumberLessEqualPredicate(), propertyName, numericValue); break; 
			case NUMERIC_GREATER_THAN: predicate = setValues(new NumberGreaterThanPredicate(), propertyName, numericValue); break;
			case NUMERIC_GREATER_OR_EQUAL: predicate = setValues(new NumberGreaterEqualPredicate(), propertyName, numericValue); break; 
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a numeric field");
		}
		return predicate;
	}

	private FilterPredicate<Number> setValues(FilterPredicate<Number> predicate, String propertyName, Number expectedValue) {
		predicate.setPropertyName(propertyName);
		predicate.setExpectedValue(expectedValue);
		return predicate;
	}

	private static class NumberEqualPredicate extends FilterPredicate<Number> {
		@Override
		public boolean apply(Number fieldValue) {
			return expectedValue.equals(fieldValue);
		}
	}

	private static class NumberNotEqualPredicate extends FilterPredicate<Number> {
		@Override
		public boolean apply(Number fieldValue) {
			return !expectedValue.equals(fieldValue);
		}
	}

	private static class NumberLessThanPredicate extends FilterPredicate<Number> {
		@Override
		public boolean apply(Number fieldValue) {
			return fieldValue != null && fieldValue.doubleValue() < expectedValue.doubleValue();
		}
	}

	private static class NumberLessEqualPredicate extends FilterPredicate<Number> {
		@Override
		public boolean apply(Number fieldValue) {
			return fieldValue != null && fieldValue.doubleValue() <= expectedValue.doubleValue();
		}
	}

	private static class NumberGreaterThanPredicate extends FilterPredicate<Number> {
		@Override
		public boolean apply(Number fieldValue) {
			return fieldValue != null && fieldValue.doubleValue() > expectedValue.doubleValue();
		}
	}

	private static class NumberGreaterEqualPredicate extends FilterPredicate<Number> {
		@Override
		public boolean apply(Number fieldValue) {
			return fieldValue != null && fieldValue.doubleValue() >= expectedValue.doubleValue();
		}
	}

}
