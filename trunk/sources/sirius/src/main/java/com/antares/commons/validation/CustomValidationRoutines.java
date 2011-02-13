package com.antares.commons.validation;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.antares.commons.util.Utils;

public class CustomValidationRoutines {

	/**
	 * Validacion de numeros reales mayores a cero
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param req
	 * @return
	 */
	public static boolean validatePositiveDouble(Object bean, ValidatorAction va, Field field, ActionMessages errors, HttpServletRequest req) {
		String strValue = ValidatorUtils.getValueAsString(bean, field.getProperty());

		if (strValue != null) {
			Double value = Utils.parseDouble(strValue);
			if (value != null && value.doubleValue() <= 0) {
				errors.add("error", new ActionMessage("errors.positiveDouble", Utils.getMessage(field.getArg(0).getKey())));
				return false;
			}
		}
		return true;
	}

}
