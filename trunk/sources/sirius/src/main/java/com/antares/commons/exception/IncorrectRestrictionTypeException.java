package com.antares.commons.exception;

/**
 * Exception que indica que el tipo de restriccion elegido es incorrecto.
 *
 * @version 1.0.0 Created 24/04/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
@SuppressWarnings("serial")
public class IncorrectRestrictionTypeException extends RuntimeException {

	public IncorrectRestrictionTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectRestrictionTypeException(String message) {
		super(message);
	}

	public IncorrectRestrictionTypeException(Throwable cause) {
		super(cause);
	}

}
