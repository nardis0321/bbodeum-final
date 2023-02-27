package com.bbodeum.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.dog.entity.Dog;
import com.bbodeum.dog.service.DogService;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;
import com.bbodeum.member.entity.Member;
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
	
	@Test
	void addDog() {
		try {
			MemberDTO member = ms.getMemberInfo("lewis@goat.com");
			
			String dateStr = "2020년 03월 19일";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
			Date bday = formatter.parse(dateStr);
			DogDTO dto = DogDTO.builder()
					.member(member)
					.dogName("스티브")
					.dogWeight((double) 25)
					.dogBday(bday)
					.dogBreed("골든 리트리버")
					.build();
			ds.addDog(dto);
		} catch (AddException e) {
			e.printStackTrace();
		} catch (FindException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
}
