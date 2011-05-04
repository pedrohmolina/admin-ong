package com.antares.commons.predicate;

import java.util.Date;

import com.antares.commons.exception.IncorrectRestrictionTypeException;
import com.antares.commons.util.Utils;
import com.google.common.base.Predicate;

/**
 * Factory Concreto que crea predicados para campos de tipo fecha
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public class DatePredicateFactory extends PredicateFactory {

	public Predicate<?> createPredicate(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Predicate<Date> predicate = null;
		Date dateValue = Utils.parseDate(value);
		switch(restrictionType) {
			case DATE_EQUAL: predicate = setValues(new DateEqualPredicate(), propertyName, dateValue); break; 
			case DATE_NOT_EQUAL: predicate = setValues(new DateNotEqualPredicate(), propertyName, dateValue); break;
			case DATE_BEFORE: predicate = setValues(new DateBeforePredicate(), propertyName, dateValue); break;
			case DATE_AFTER: predicate = setValues(new DateAfterPredicate(), propertyName, dateValue); break; 
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a date field");
		}
		return predicate;
	}

	private FilterPredicate<Date> setValues(FilterPredicate<Date> predicate, String propertyName, Date expectedValue) {
		predicate.setPropertyName(propertyName);
		predicate.setExpectedValue(expectedValue);
		return predicate;
	}

	private static class DateEqualPredicate extends FilterPredicate<Date> {
		@Override
		public boolean apply(Date fieldValue) {
			return expectedValue.equals(fieldValue);
		}
	}

	private static class DateNotEqualPredicate extends FilterPredicate<Date> {
		@Override
		public boolean apply(Date fieldValue) {
			return !expectedValue.equals(fieldValue);
		}
	}

	private static class DateBeforePredicate extends FilterPredicate<Date> {
		@Override
		public boolean apply(Date fieldValue) {
			return expectedValue.after(fieldValue);
		}
	}

	private static class DateAfterPredicate extends FilterPredicate<Date> {
		@Override
		public boolean apply(Date fieldValue) {
			return expectedValue.before(fieldValue);
		}
	}

}
