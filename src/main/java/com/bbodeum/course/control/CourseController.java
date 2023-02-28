package com.bbodeum.course.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.course.dto.CourseDTOLight;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.course.service.CourseService;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.FindException;

@RestController
@RequestMapping("course/*")
public class CourseController {
	@Autowired
	private CourseService service;
	
	@GetMapping(value="classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseList(int page) throws FindException {
		PageBean<CourseDTOLight> bean = service.getCourseAll(page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}
	
	@GetMapping(value="classes/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseList(@PathVariable("courseId") Long courseId) throws FindException {
		CourseDTOLight dto = service.getCourseById(courseId);
		return new ResponseEntity<>(dto, HttpStatus.OK); 
	}
	
	@GetMapping(value="info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseInfoList() throws FindException {
		List<CourseInfoDTO> list = service.getAllCourseInfo();
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}
}
