package com.bbodeum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.service.ApplyService;
import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.service.CourseService;
import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.dog.service.DogService;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;

@SpringBootTest
class ApplyServiceTest {
	@Autowired
	ApplyService as;

	@Test
	void testGetApplyById() {
		try {
			ApplyDTO dto = as.getById(null, null);
			System.out.println(dto);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddApply() {
		try {
			DogDTO dog = DogDTO.builder().dogId(2L).build();
			CourseDTO c = CourseDTO.builder().courseId(3L).build();
			ApplyDTO dto = ApplyDTO.builder().dog(dog).course(c).build();

			as.addApply(dto);
			
		} catch (AddException e) {
			e.printStackTrace();
		}
	}
	
}
