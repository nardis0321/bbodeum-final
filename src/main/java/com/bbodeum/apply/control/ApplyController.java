package com.bbodeum.apply.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.service.ApplyService;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.FindException;

@RestController
@RequestMapping("applies/*")
public class ApplyController {
	@Autowired
	private ApplyService service;
	
	@GetMapping(value="/dogs/{dogId}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> appliesByDog(@PathVariable("dogId") Long dogId,@PathVariable("page") int page) throws FindException {
		PageBean<ApplyDTO> bean = service.getByDog(dogId, page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}

	@GetMapping(value="/classes/{courseId}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> appliesByCourse(@PathVariable("courseId") Long courseId,@PathVariable("page") int page) throws FindException {
		PageBean<ApplyDTO> bean = service.getByCourse(courseId, page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}
}
