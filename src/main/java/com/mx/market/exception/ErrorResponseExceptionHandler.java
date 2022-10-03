package com.mx.market.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mx.market.model.ErrorRespuesta;

@RestControllerAdvice
public class ErrorResponseExceptionHandler {
	
	@ExceptionHandler(MarketException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public final ErrorRespuesta exception(MarketException me, HttpServletRequest request) {
		
		ErrorRespuesta error = new ErrorRespuesta();
		
		List<String> resp = new ArrayList<>();
		resp.add(me.getMessage());
		error.setErrors(resp );
		return error;
		
	}

}
