package com.antares.commons.restrictions;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.exception.IncorrectRestrictionTypeException;
import com.antares.commons.util.Utils;

public class NumericRestrictionBuilder extends RestrictionBuilder {

	public Criterion buildRestriction(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Criterion restriction = null;
		Number numericValue = Utils.parseDouble(value);
		switch(restrictionType) {
			case NUMERIC_EQUAL: restriction = Restrictions.eq(propertyName, numericValue); break; 
			case NUMERIC_NOT_EQUAL: restriction = Restrictions.ne(propertyName, numericValue); break;
			case NUMERIC_LESS_THAN: restriction = Restrictions.lt(propertyName, numericValue); break;
			case NUMERIC_LESS_OR_EQUAL: restriction = Restrictions.le(propertyName, numericValue); break; 
			case NUMERIC_GREATER_THAN: restriction = Restrictions.gt(propertyName, numericValue); break;
			case NUMERIC_GREATER_OR_EQUAL: restriction = Restrictions.ge(propertyName, numericValue); break; 
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a numeric field");
		}
		return restriction;
	}
}
