package com.helpdesk.HelpDesk.services.exceptions;

public class ObjectNotFoudException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoudException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ObjectNotFoudException(String msg) {
		super(msg);
	}

}
