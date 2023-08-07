package com.swamy.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.swamy.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setStatus(HttpStatus.NOT_FOUND);
		errorDetails.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setPath(webRequest.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(exception.getMessage());
		errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorDetails.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setPath(webRequest.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
