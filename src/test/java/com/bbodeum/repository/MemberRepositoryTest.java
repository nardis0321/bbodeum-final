package com.bbodeum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.member.entity.Member;
import com.bbodeum.member.entity.MemberStatus;
import com.bbodeum.member.repository.MemberRepository;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository mr;
	
	@Test
	void testSave() {
		MemberStatus memStat = MemberStatus.NORMAL;
		Member member = new Member("lewis@goat.com", "123theBest", "루이스 해밀턴", "010-1144-2314", memStat);
		mr.save(member);
	}
	
	@Test
	void testfindById() {
		Optional<Member> optM = mr.findById("lewis@goat.com");
		assertTrue(optM.isPresent());
		
		Member m = optM.get();
		String expectedName = "루이스 해밀턴";
		assertEquals(expectedName, m.getMemName());
	}

}
