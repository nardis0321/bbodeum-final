package com.bbodeum.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class ControllerAdvice {
		@ExceptionHandler(Exception.class)
		@ResponseBody
		public ResponseEntity<?> ExceptionHandler(Exception e){
			e.printStackTrace();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json;charset=UTF-8");
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
}
