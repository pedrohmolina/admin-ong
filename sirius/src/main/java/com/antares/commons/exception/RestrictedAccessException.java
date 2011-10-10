package com.antares.commons.exception;

/**
 * Exception que indica que el usuario no tiene el acceso necesario sobre el recurso que intenta usar
 *
 * @version 1.0.0 Created 03/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
@SuppressWarnings("serial")
public class RestrictedAccessException extends RuntimeException {

	public RestrictedAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestrictedAccessException(String message) {
		super(message);
	}

	public RestrictedAccessException(Throwable cause) {
		super(cause);
	}

}
