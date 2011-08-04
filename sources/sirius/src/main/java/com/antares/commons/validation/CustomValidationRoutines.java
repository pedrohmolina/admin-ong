package com.antares.commons.validation;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.antares.commons.util.Cuit;
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
		if (Utils.isNotNullNorEmpty(strValue)) {
			Double value = Utils.parseDouble(strValue);
			if (value == null || value.doubleValue() < 0) {
				errors.add("error", new ActionMessage("errors.positiveDouble", Utils.getMessage(field.getArg(0).getKey())));
				return false;
			}
		}
		return true;
	}

	/**
	 * Validacion para CUITs
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param req
	 * @return
	 */
	public static boolean validateCuit(Object bean, ValidatorAction va, Field field, ActionMessages errors, HttpServletRequest req) {
		String cuit = ValidatorUtils.getValueAsString(bean, field.getProperty());
		if (Utils.isNotNullNorEmpty(cuit) && !Cuit.validar(cuit)) {
			errors.add("error", new ActionMessage("errors.cuit", Utils.getMessage(field.getArg(0).getKey())));
			return false;
		}
		return true;
	}

	/**
	 * Evalua si una ponderacion nueva, sumada a las existentes, provoca que se exceda del 100%
	 * 
	 * @param ponderacionTotal ponderacion total del resto de elementos del mismo nivel
	 * @param nuevaPonderacion poderacion a ingresar
	 * @param errors
	 * @param messageKey
	 * @return
	 */
	public static boolean validatePonderacion(Integer ponderacionTotal, Integer nuevaPonderacion, ActionMessages errors, String messageKey) {
		if (ponderacionTotal + nuevaPonderacion > 100) {
			Integer diff = Math.abs(100 - (ponderacionTotal + nuevaPonderacion));
			errors.add("error", new ActionMessage("errors.ponderiacion", Utils.getMessage(messageKey), diff));
			return false;
		}
		return true;
	}

}
