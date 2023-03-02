package com.bbodeum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.trainer.entity.Trainer;
import com.bbodeum.trainer.entity.TrainerStatus;
import com.bbodeum.trainer.repository.TrainerRepository;

@SpringBootTest
class TrainerRepositoryTest {
	@Autowired
	private TrainerRepository tr;
	
	@Test
	void testSave() {
		TrainerStatus trStat = TrainerStatus.TRAINER;
		Trainer trainer = new Trainer("TR0000", "333", "강형욱",
							"뽀듬 훈련사 강형욱입니다", "강아지대학교 석사", 
							"보듬 훈련소 소장", "강아지 훈련사 1급",
							trStat);
		tr.save(trainer);
	}
	
	@Test
	void testfindById() {
		Optional<Trainer> optT = tr.findById("TR0000");
		assertTrue(optT.isPresent());
		
		Trainer trainer = optT.get();
		String expectedTrName = "강형욱";
		assertEquals(expectedTrName, trainer.getTrName());
		String expectedCertificates = "강아지 훈련사 1급";
		assertEquals(expectedCertificates, trainer.getTrCertificates());
	}
	
	@Test
	void testFindLastTrId() {
		Trainer entity = tr.findFirstByOrderByTrIdDesc();
		System.out.println(entity.getTrId());
	}

}
