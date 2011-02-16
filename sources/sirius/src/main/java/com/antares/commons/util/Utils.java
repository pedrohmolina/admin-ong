package com.antares.commons.util;

import static com.antares.sirius.base.Constants.DEFAULT_DATE_FORMAT;
import static com.antares.sirius.base.Constants.DEFAULT_DECIMAL_FORMAT;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

import com.antares.sirius.model.Ponderable;

public class Utils {

	private static final Logger logger = Logger.getLogger(Utils.class);

	private static MessageSource messageSource;

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

	private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat(DEFAULT_DECIMAL_FORMAT);

	public void setMessageSource(MessageSource messageSource) {
		Utils.messageSource = messageSource;
	}

	public void setDatePattern(String datePattern) {
		Utils.DATE_FORMAT = new SimpleDateFormat(datePattern);
	}

	/**
	 * Resuelve el valor del text representado por la clave key.
	 * 
	 * @param key clave a resolver.
	 * @return
	 */
	public static String getMessage(String key) {
		String result = null;
		try {
			result = messageSource.getMessage(key, null, null);
		} catch (Exception e) {
			logger.debug("Error al resolver la clave " + key, e);
		}
		return result;
	}

	/**
	 * Formatea la fecha usando el formato predeterminado del sistema
	 * 
	 * @param fecha a formatear
	 * @return
	 */
	public static String formatDate(Date date) {
		String strDate = "";
		if (date != null) {
			strDate = DATE_FORMAT.format(date);
		}
		return strDate;
	}

	/**
	 * Parsea la fecha usando el formato predeterminado del sistema
	 * 
	 * @param dateStr fecha a parsear
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		Date date = null;
		try {
			if (isNotNullNorEmpty(dateStr)) {
				date = DATE_FORMAT.parse(dateStr);
			}
		} catch (ParseException e) {
			// Si no se puede formatear, devuelvo null
		}
		return date;
	}

	/**
	 * Formatea el número double usando el formato predeterminado del sistema
	 * 
	 * @param number double a formatear
	 * @return
	 */
	public static String formatDouble(Double number) {
		String strDouble = "";
		if (number != null) {
			strDouble = DECIMAL_FORMAT.format(number);
		}
		return strDouble;
	}

	/**
	 * Parsea el número double usando el formato predeterminado del sistema, en caso de no poder parsearlo devuelve null
	 * 
	 * @param doubleStr double a parsear
	 * @return
	 */
	public static Double parseDouble(String doubleStr) {
		Double doubleNum = null;
		try {
			if (isNotNullNorEmpty(doubleStr)) {
				doubleNum = DECIMAL_FORMAT.parse(doubleStr).doubleValue();
			}
		} catch (ParseException e) {
			// Si no se puede formatear, devuelvo null
		}
		return doubleNum;
	}

	/**
	 * Parsea el número entero, en caso de no poder parsearlo devuelve null
	 * 
	 * @param intStr entero a parsear
	 * @return
	 */
	public static Integer parseInteger(String intStr) {
		Integer integer = null;
		try {
			if (isNotNullNorEmpty(intStr)) {
				integer = Integer.parseInt(intStr);
			}
		} catch (NumberFormatException e) {
			// Si no se puede formatear, devuelvo null
		}
		return integer;
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotNullNorEmpty(String str) {
		return str != null && str.length() > 0;
	}

	public static boolean excedePonderacion(Integer nuevaPonderacion, Collection<? extends Ponderable> ponderables, Integer idPonderacionEditada) {
		Integer ponderacion = 0;
		for (Ponderable ponderable : ponderables) {
			if (ponderable.isActivo() && (!ponderable.getId().equals(idPonderacionEditada))) {
				ponderacion += ponderable.getPonderacion();
			}
		}
		return ponderacion + nuevaPonderacion > 100;
	}

	public static String encode(String str) {
		String md5Str = null;
		try {
		    MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
		    digest.update(str.getBytes("UTF8"));
		    byte[] hash = digest.digest();
		    md5Str = "";
		    for (int i = 0; i < hash.length; i++) {
	        	md5Str += Integer.toHexString((0x000000ff & hash[i]) | 0xffffff00).substring(6);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}
}
