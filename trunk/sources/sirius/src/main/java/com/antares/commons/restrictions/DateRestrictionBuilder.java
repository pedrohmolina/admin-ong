package com.antares.commons.restrictions;

import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.exception.IncorrectRestrictionTypeException;
import com.antares.commons.util.Utils;

public class DateRestrictionBuilder extends RestrictionBuilder {

	public Criterion buildRestriction(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Criterion restriction = null;
		Date dateValue = Utils.parseDate(value);
		switch(restrictionType) {
			case DATE_EQUAL: restriction = Restrictions.eq(propertyName, dateValue); break; 
			case DATE_NOT_EQUAL: restriction = Restrictions.ne(propertyName, dateValue); break;
			case DATE_BEFORE: restriction = Restrictions.lt(propertyName, dateValue); break;
			case DATE_AFTER: restriction = Restrictions.gt(propertyName, dateValue); break; 
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a date field");
		}
		return restriction;
	}
}
