package com.helpdesk.HelpDesk.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public DataIntegrityViolationException(String msg) {
		super(msg);
	}

}
