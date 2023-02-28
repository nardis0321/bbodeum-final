package com.bbodeum.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.service.ApplyService;
import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

@SpringBootTest
class ApplyServiceTest {
	@Autowired
	ApplyService as;

	@Test
	void testGetApplyById() {
		try {
			ApplyDTO dto = as.getById(2L, 5L);
			System.out.println(dto);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetAppliesByDog() {
		try {
			List<ApplyDTO> list = as.getByDog(7L);
			list.forEach((dto)->{
				System.out.println(dto);
			});
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetAppliesByCourse() {
		try {
			List<ApplyDTO> list = as.getByCourse(5L);
			list.forEach((dto)->{
				System.out.println(dto);
			});
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddApply() {
		try {
			as.addApply(7L, 3L);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdateApply() {
		try {
			as.dropApply(7L, 3L);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
