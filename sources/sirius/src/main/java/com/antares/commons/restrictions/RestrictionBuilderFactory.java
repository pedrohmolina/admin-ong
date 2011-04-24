package com.antares.commons.restrictions;

import com.antares.commons.exception.PropertyTypeNotSupportedException;

public class RestrictionBuilderFactory {

	public static RestrictionBuilder getRestrictionBuilder(PropertyType propertyType) throws PropertyTypeNotSupportedException {
		RestrictionBuilder restrictionBuilder = null;
		switch (propertyType) {
			case NUMERIC: restrictionBuilder = new NumericRestrictionBuilder(); break; 
			case TEXT: restrictionBuilder = new TextRestrictionBuilder(); break; 
			case DATE: restrictionBuilder = new DateRestrictionBuilder(); break; 
			case BOOLEAN: restrictionBuilder = new BooleanRestrictionBuilder(); break; 
			default: throw new PropertyTypeNotSupportedException("The property type " + propertyType + " is not supported");
		}
		return restrictionBuilder;
	}
}
