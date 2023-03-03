package com.bbodeum.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<?> ExceptionHandler(Exception e){
		e.printStackTrace();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");
		return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<?> validException(MethodArgumentNotValidException e) {
		e.printStackTrace();
		Map<String, Object> map = new HashMap<>();
//		map.put("success", false);
		map.put("msg", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}