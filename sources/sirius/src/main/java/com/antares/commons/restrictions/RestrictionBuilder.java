package com.antares.commons.restrictions;

import org.hibernate.criterion.Criterion;

import com.antares.commons.exception.IncorrectRestrictionTypeException;

public abstract class RestrictionBuilder {

	public abstract Criterion buildRestriction(String propertyName, RestrictionType restrictionType, String value) throws IncorrectRestrictionTypeException;

}
