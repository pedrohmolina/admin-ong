package com.antares.commons.restrictions;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.antares.commons.exception.IncorrectRestrictionTypeException;

public class BooleanRestrictionBuilder extends RestrictionBuilder {

	public Criterion buildRestriction(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException {
		Criterion restriction = null;
		switch(restrictionType) {
			case BOOLEAN_TRUE: restriction = Restrictions.eq(propertyName, TRUE); break; 
			case BOOLEAN_FALSE: restriction = Restrictions.eq(propertyName, FALSE); break;
			default: throw new IncorrectRestrictionTypeException("The restricion " + restrictionType + " is not permitted for a boolean field");
		}
		return restriction;
	}
}
