package com.antares.commons.criterion;

import org.hibernate.criterion.MatchMode;
import org.hibernate.dialect.Dialect;

@SuppressWarnings("serial")
public class IlikeExpression extends LikeExpression {

    public IlikeExpression(String propertyName, Object value) {         
    	super(propertyName, value);     
    }      

    public IlikeExpression(String propertyName, String value, MatchMode matchMode) {         
    	super(propertyName, value, matchMode);      
    }      

    public IlikeExpression(String propertyName, String value, Character escapeChar) {         
    	super(propertyName, value, escapeChar);     
    }      

    @Override     
    protected String lhs(Dialect dialect, String column) {         
    	return dialect.getLowercaseFunction() + '(' + column + ')';     
    }      

    @Override     
    protected String typedValue(String value) {         
    	return super.typedValue(value).toLowerCase();     
    }  

}
