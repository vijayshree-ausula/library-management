package com.library.api.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.library.api.exception.BookNotAvailableException;
import com.library.api.exception.BookNotFoundException;
import com.library.api.exception.MemberNotFoundException;

@RestControllerAdvice
@Validated
public class ApplicationExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BookNotFoundException.class)
	public Map<String, String> handleBusinessException(BookNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("errorCode", "500");
		errorMap.put("erroMessage", ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MemberNotFoundException.class)
	public Map<String, String> handleBusinessException(MemberNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("errorCode", "500");
		errorMap.put("erroMessage", ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BookNotAvailableException.class)
	public Map<String, String> handleAvailabilityException(BookNotAvailableException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("erroMessage", ex.getMessage());
		return errorMap;
	}
}
