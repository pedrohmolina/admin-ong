package com.antares.commons.predicate;

import com.google.common.base.Predicate;

public abstract class FilterPredicate<T> implements Predicate<T>{

	protected String propertyName;
	protected T expectedValue;
	
	@Override
	public abstract boolean apply(T fieldValue);

	public T getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(T expectedValue) {
		this.expectedValue = expectedValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
