package com.bbodeum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.dog.entity.Dog;
import com.bbodeum.dog.entity.DogStatus;
import com.bbodeum.dog.repository.DogRepository;
import com.bbodeum.member.entity.Member;
import com.bbodeum.member.repository.MemberRepository;
@SpringBootTest
class DogRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private DogRepository dr;
	
	@Autowired
	private MemberRepository mr;

	@Test
	void testDogSave() throws ParseException {
		Optional<Member> optM = mr.findById("lewis@goat.com");
		assertTrue(optM.isPresent());
		
		String dateStr = "2016년 06월 19일";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date bday = formatter.parse(dateStr);
		Dog d = Dog.builder()
				.member(optM.get())
				.dogName("로스코")
				.dogWeight((double) 20)
				.dogBday(bday)
				.dogBreed("핏불")
				.build();
		dr.save(d);
	}
	
	@Test
	void testFindDogByMember() {
		Optional<Member> optM = mr.findById("lewis@goat.com");
		assertTrue(optM.isPresent());
	
		List<Dog> list = dr.findByMember(optM.get());
		list.forEach((Dog)->{
			logger.info("Member: "+ Dog.getMember());
			logger.info(Dog.getDogName());
			logger.info(Dog.getDogBreed());
			logger.info(Dog.getDogBday().toString());
		});
	}

	@Test
	void testFindDogByMemAndName() {
		Optional<Member> optM = mr.findById("lewis@goat.com");
		assertTrue(optM.isPresent());
		
		List<Dog> list = dr.findByMemberAndDogName(optM.get(), "로스코");
		int expectedListLength = 1;
		assertEquals(expectedListLength, list.size());

		Dog dog = list.get(0);
		String expectedDogMemName = "루이스 해밀턴";
		assertEquals(expectedDogMemName, dog.getMember().getMemName());
		String expectedDogBreed = "핏불";
		assertEquals(expectedDogBreed, dog.getDogBreed());
		float expectedDogWeight = 25;
		assertEquals(expectedDogWeight, dog.getDogWeight());
		String expectedDogBday = "2016-06-19";
		assertEquals(expectedDogBday, dog.getDogBday().toString());
	}
	
}
