package com.antares.commons.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.beanutils.PropertyUtils;


public class JRListaDataSource implements JRDataSource {

	Iterator dataValues;
	
	Object element;

	public JRListaDataSource(Collection dataValues) {
		this.dataValues = dataValues.iterator();	

	}

	public boolean next() throws JRException {
		if (dataValues.hasNext()) {
			this.element = (Object) dataValues.next();
			return true;
		}
		return false;
	}

	public Object getFieldValue(JRField field) throws JRException {

		try {
			
			Object o = null;
			if (element instanceof Map) {
				o = ((Map) element).get(field.getName());
			} else {
				o =	PropertyUtils.getNestedProperty(element, field.getName());
			}

			if (o instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				o = sdf.format(o);
			}
			
			return o == null ? "" : o.toString();			
		} catch (Exception e) {
			//throw new Exception(e);
			return "";
		}
	}
}
