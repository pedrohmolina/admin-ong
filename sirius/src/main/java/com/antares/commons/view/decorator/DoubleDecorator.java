/**
 *
 */
package com.antares.commons.view.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.antares.commons.util.Utils;

public class DoubleDecorator implements DisplaytagColumnDecorator {

	public Object decorate(Object columnValue, PageContext pageContext, MediaTypeEnum media) throws DecoratorException {
		if (Double.class.isAssignableFrom(columnValue.getClass())) {
			return Utils.formatDouble((Double)columnValue);
		}
		return columnValue.toString();
	}

}
