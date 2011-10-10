package com.antares.commons.predicate;

import com.antares.commons.exception.IncorrectRestrictionTypeException;
import com.google.common.base.Predicate;

/**
 * Factory Concreto que crea predicados para campos de tipo texto
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public class TextPredicateFactory extends PredicateFactory {

	public Predicate<?> createPredicate(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Predicate<String> predicate = null;
		switch(restrictionType) {
			case TEXT_EQUAL: predicate = setValues(new TextEqualPredicate(), propertyName, value); break; 
			case TEXT_NOT_EQUAL: predicate = setValues(new TextNotEqualPredicate(), propertyName, value); break;
			case TEXT_CONTAIN: predicate = setValues(new TextContainPredicate(), propertyName, value); break;
			case TEXT_NOT_CONTAIN: predicate = setValues(new TextNotContainPredicate(), propertyName, value); break; 
			case TEXT_START_WITH: predicate = setValues(new TextStartPredicate(), propertyName, value); break;
			case TEXT_DONT_START_WITH: predicate = setValues(new TextNotStartPredicate(), propertyName, value); break; 
			case TEXT_END_WITH: predicate = setValues(new TextEndPredicate(), propertyName, value); break;
			case TEXT_DONT_END_WITH: predicate = setValues(new TextNotEndPredicate(), propertyName, value); break; 
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a text field");
		}
		return predicate;
	}

	private FilterPredicate<String> setValues(FilterPredicate<String> predicate, String propertyName, String expectedValue) {
		predicate.setPropertyName(propertyName);
		predicate.setExpectedValue(expectedValue);
		return predicate;
	}

	private static class TextEqualPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return expectedValue.equals(fieldValue);
		}
	}

	private static class TextNotEqualPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return !expectedValue.equals(fieldValue);
		}
	}

	private static class TextContainPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return fieldValue != null && fieldValue.toUpperCase().contains(expectedValue.toUpperCase());
		}
	}

	private static class TextNotContainPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return fieldValue == null || !fieldValue.toUpperCase().contains(expectedValue.toUpperCase());
		}
	}

	private static class TextStartPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return fieldValue != null && fieldValue.toUpperCase().startsWith(expectedValue.toUpperCase());
		}
	}

	private static class TextNotStartPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return fieldValue == null || !fieldValue.toUpperCase().startsWith(expectedValue.toUpperCase());
		}
	}

	private static class TextEndPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return fieldValue != null && fieldValue.toUpperCase().endsWith(expectedValue.toUpperCase());
		}
	}

	private static class TextNotEndPredicate extends FilterPredicate<String> {
		@Override
		public boolean apply(String fieldValue) {
			return fieldValue == null || !fieldValue.toUpperCase().endsWith(expectedValue.toUpperCase());
		}
	}

}
