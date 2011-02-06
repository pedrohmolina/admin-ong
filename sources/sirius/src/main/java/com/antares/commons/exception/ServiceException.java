package com.antares.commons.exception;

/**
 * Exception de la capa de servicios.
 *
 * @version 1.0.0 Created 25/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception {

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
