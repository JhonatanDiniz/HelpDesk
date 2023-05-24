package com.helpdesk.HelpDesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpdesk.HelpDesk.services.exceptions.ObjectNotFoudException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoudException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoudException ex,	HttpServletRequest request) {
		Long timestamp = System.currentTimeMillis();
		Integer status = HttpStatus.NOT_FOUND.value();
		String error = "Object Not Found!";
		String msg = ex.getMessage();
		String path = request.getRequestURI();
		StandardError err = new StandardError(timestamp, status, error, msg, path);

		return ResponseEntity.status(status).body(err);

	}

}
