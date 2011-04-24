package com.antares.commons.restrictions;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.exception.IncorrectRestrictionTypeException;

public class TextRestrictionBuilder extends RestrictionBuilder {

	public Criterion buildRestriction(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Criterion restriction = null;
		switch(restrictionType) {
			case TEXT_EQUAL: restriction = Restrictions.eq(propertyName, value).ignoreCase(); break; 
			case TEXT_NOT_EQUAL: restriction = Restrictions.ne(propertyName, value).ignoreCase(); break;
			case TEXT_CONTAIN: restriction = Restrictions.ilike(propertyName, "%" + value + "%"); break;
			case TEXT_NOT_CONTAIN: restriction = Restrictions.not(Restrictions.ilike(propertyName, "%" + value + "%")); break; 
			case TEXT_START_WITH: restriction = Restrictions.ilike(propertyName, value + "%"); break;
			case TEXT_DONT_START_WITH: restriction = Restrictions.not(Restrictions.ilike(propertyName, value + "%")); break; 
			case TEXT_END_WITH: restriction = Restrictions.ilike(propertyName, "%" + value); break;
			case TEXT_DONT_END_WITH: restriction = Restrictions.not(Restrictions.ilike(propertyName, "%" + value)); break; 
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a text field");
		}
		return restriction;
	}
}
