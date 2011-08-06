package com.antares.commons.validation;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.antares.commons.util.Cuit;
import com.antares.commons.util.Utils;

public class CustomValidationRoutines {

	private static final String POSITIVE_DOUBLE_MSG_KEY = "errors.positiveDouble";   
	private static final String CUIT_MSG_KEY = "errors.cuit";   
	private static final String PONDERACION_MSG_KEY = "errors.ponderiacion";   
	private static final String BEGIN_DATE_FIELD_NAME = "beginDateField";   
	private static final String DATES_RANGE_INVALID_MSG_KEY = "errors.datesrangeinvalid";

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
				errors.add(field.getKey(), new ActionMessage(POSITIVE_DOUBLE_MSG_KEY, Utils.getMessage(field.getArg(0).getKey())));
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
			errors.add(field.getKey(), new ActionMessage(CUIT_MSG_KEY, Utils.getMessage(field.getArg(0).getKey())));
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
			errors.add("error", new ActionMessage(PONDERACION_MSG_KEY, Utils.getMessage(messageKey), diff));
			return false;
		}
		return true;
	}

    public static boolean validateDaterange(Object bean, ValidatorAction va, Field field, ActionMessages errors, HttpServletRequest request) {   
        boolean isValid = true;   
        
        String endDate = ValidatorUtils.getValueAsString(bean, field.getProperty());   
        String beginDate = ValidatorUtils.getValueAsString(bean, field.getVarValue(BEGIN_DATE_FIELD_NAME));   
        
        if (GenericValidator.isBlankOrNull(endDate) || GenericValidator.isBlankOrNull(beginDate)) {   
            return isValid;   
        }   

        if (!isValidDateRange(beginDate, endDate)) {   
            isValid=false;   
			errors.add(field.getKey(), new ActionMessage(DATES_RANGE_INVALID_MSG_KEY, Utils.getMessage(field.getArg(0).getKey())));
        }   
        return isValid;   
    }   

    private static boolean isValidDateRange(String beginDate, String endDate) {   
        // Convert both dates into their calendar objects.   
        Calendar begin = new GregorianCalendar();   
        Calendar end = new GregorianCalendar();   
        begin.setTime(Utils.parseDate(beginDate));   
        end.setTime(Utils.parseDate(endDate));   
        return !end.before(begin);
    }
    
}
