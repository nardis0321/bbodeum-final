package com.bbodeum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.service.ApplyService;
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
		ApplyDTO dto = ApplyDTO.builder()
//				.dogId(1L)
//				.courseId(43L)
				.build();
		try {
			as.addApply(dto);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}
	
}
