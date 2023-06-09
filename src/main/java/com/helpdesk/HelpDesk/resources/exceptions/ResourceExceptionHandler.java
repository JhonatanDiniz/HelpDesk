package com.helpdesk.HelpDesk.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.helpdesk.HelpDesk.services.exceptions.DataIntegrityViolationException;
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
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,	HttpServletRequest request) {
		Long timestamp = System.currentTimeMillis();
		Integer status = HttpStatus.BAD_REQUEST.value();
		String error = "Violação de dados";
		String msg = ex.getMessage();
		String path = request.getRequestURI();
		StandardError err = new StandardError(timestamp, status, error, msg, path);

		return ResponseEntity.status(status).body(err);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> notValidException(MethodArgumentNotValidException ex,	HttpServletRequest request) {
		Long timestamp = System.currentTimeMillis();
		Integer status = HttpStatus.BAD_REQUEST.value();
		String error = "Validation erro";
		String msg = "Erro na validação dos campos";
		String path = request.getRequestURI();
		ValidationErro erro = new ValidationErro(timestamp, status, error, msg, path);
		
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			erro.addErros(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(erro);

	}

}
