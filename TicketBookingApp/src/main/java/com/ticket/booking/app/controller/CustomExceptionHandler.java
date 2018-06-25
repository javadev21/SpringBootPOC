package com.ticket.booking.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ticket.booking.dto.Response;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Response> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		Response errorResponse = new Response(HttpStatus.OK.value(), "Server Error", details);
		return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult result = ex.getBindingResult();
		List<String> errorMessages = result.getAllErrors().stream()
				.map(objectError -> messageSource.getMessage(objectError, new Locale("EN")))
				.collect(Collectors.toList());

		Response errorResponse = new Response(HttpStatus.BAD_REQUEST.value(), "Validation Failed", errorMessages);
		return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
