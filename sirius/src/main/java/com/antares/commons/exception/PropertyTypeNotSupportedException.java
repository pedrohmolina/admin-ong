package com.antares.commons.exception;

/**
 * Exception que indica que el tipo de restriccion elegido es incorrecto.
 *
 * @version 1.0.0 Created 24/04/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
@SuppressWarnings("serial")
public class PropertyTypeNotSupportedException extends RuntimeException {

	public PropertyTypeNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyTypeNotSupportedException(String message) {
		super(message);
	}

	public PropertyTypeNotSupportedException(Throwable cause) {
		super(cause);
	}

}
