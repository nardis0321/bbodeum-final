package com.bbodeum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;
import com.bbodeum.member.entity.MemberStatus;
import com.bbodeum.member.service.MemberService;
@SpringBootTest
class MemberServiceTest {
	@Autowired
	MemberService ms;

	@Test
	void testSignIn() {
		String email = "lewis@goat.com";
		String pwd = "123theBest";
		try {
			ms.signIn(email,pwd);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testSignUp() {
		MemberDTO dto = MemberDTO.builder()
				.memEmail("cream@cake.com")
				.memPwd("123")
				.memName("크림블")
				.memPhone("111-333-4444")
				.memStatus(MemberStatus.NORMAL)
				.build();
		try {
			ms.signUp(dto);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetMemberInfo() {
		try {
			ms.getMemberInfo("cream@cake.com");
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdateMemberInfo() {
		MemberDTO dto = MemberDTO.builder()
				.memEmail("cream@cake.com")
				.memPhone("010-233-5344")
				.build();
		try {
			ms.updateMemberInfo(dto);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
	}

}
