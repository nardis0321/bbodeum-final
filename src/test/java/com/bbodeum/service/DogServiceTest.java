package com.bbodeum.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.dog.service.DogService;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;
import com.bbodeum.member.service.MemberService;
@SpringBootTest
class DogServiceTest {
	@Autowired
	DogService ds;
	@Autowired
	MemberService ms;

	@Test
	void testGetDog() {
		try {
			DogDTO dto = ds.getDog(2L);
			System.out.println(dto);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetDogs() {
		try {
			MemberDTO dto = ms.getMemberInfo("lewis@goat.com");
			List<DogDTO> list = ds.getDogsByMem(dto);
			for(DogDTO d : list) {
				System.out.println("--"+ d.getMember()+ "--");
				System.out.println(d);
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdateDog() {
		try {
		DogDTO dto = DogDTO.builder()
				.dogId(2L)
				.dogWeight(17.8)
				.build();
		ds.updateDog(dto);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
	}

	
}
