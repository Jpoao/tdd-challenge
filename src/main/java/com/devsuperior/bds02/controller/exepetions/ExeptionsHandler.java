package com.devsuperior.bds02.controller.exepetions;

import java.io.Serializable;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devsuperior.bds02.services.expetion.DataViolationException;
import com.devsuperior.bds02.services.expetion.EntityNotFoundExceptions;


@RestControllerAdvice
public class ExeptionsHandler implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(EntityNotFoundExceptions.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundExceptions e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		err.setError("Request not found");
		err.setMsg(e.getMessage());
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		return ResponseEntity.status(status).body(err);	
	}
	
	@ExceptionHandler(DataViolationException.class)
	public ResponseEntity<StandardError> constraintViolation(DataViolationException e, HttpServletRequest request){
		StandardError err = new StandardError();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		err.setError("Data integarty Violation");
		err.setMsg(e.getMessage());
		err.setTimestamp(Instant.now());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		return ResponseEntity.status(status).body(err);	
	}
	
	
}
