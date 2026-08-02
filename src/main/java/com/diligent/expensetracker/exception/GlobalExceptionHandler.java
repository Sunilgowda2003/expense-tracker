package com.diligent.expensetracker.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(ExpenseNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleExpenseNotFoundException(
	            ExpenseNotFoundException ex) {

	        Map<String, Object> response = new LinkedHashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.NOT_FOUND.value());
	        response.put("error", "Not Found");
	        response.put("message", ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    // Handle Validation Exceptions
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, Object>> handleValidationException(
	            MethodArgumentNotValidException ex) {

	        Map<String, Object> response = new LinkedHashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.BAD_REQUEST.value());
	        response.put("error", "Validation Failed");

	        Map<String, String> errors = new LinkedHashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errors.put(error.getField(), error.getDefaultMessage()));

	        response.put("messages", errors);

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    // Handle Any Other Exception
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {

	        Map<String, Object> response = new LinkedHashMap<>();
	        response.put("timestamp", LocalDateTime.now());
	        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        response.put("error", "Internal Server Error");
	        response.put("message", ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
