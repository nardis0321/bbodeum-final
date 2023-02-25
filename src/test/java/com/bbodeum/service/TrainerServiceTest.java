package com.bbodeum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.trainer.dto.TrainerDTO;
import com.bbodeum.trainer.service.TrainerService;
@SpringBootTest
class TrainerServiceTest {
	@Autowired
	TrainerService ts;

	@Test
	void testGetTr() {
		try {
			TrainerDTO t = ts.getTrainerInfo("TR0000");
			System.out.println(t);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testSignUp() {
		TrainerDTO dto = TrainerDTO.builder()
				.trId("TR0001")
				.trPwd("jjj")
				.trName("송태섭")
				.trSummary("반려견들의 친구")
				.trEducation("강아지 행동학 전공")
				.trExperience("보듬 훈련사")
				.trCertificates("반려견 훈련사 2급")
				.build();
		try {
			ts.signUp(dto);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdateMemberInfo() {
		TrainerDTO dto = TrainerDTO.builder()
				.trId("TR0001")
				.trExperience("북산 훈련소 트레이너")
				.build();
		try {
			ts.updateTrainerInfo(dto);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
	}

}
