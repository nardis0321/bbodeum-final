package com.bbodeum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.apply.entity.Apply;
import com.bbodeum.apply.entity.ApplyId;
import com.bbodeum.apply.entity.ApplyStatus;
import com.bbodeum.apply.repository.ApplyRepository;
import com.bbodeum.course.entity.Course;
import com.bbodeum.course.repository.CourseRepository;
import com.bbodeum.dog.entity.Dog;
import com.bbodeum.dog.repository.DogRepository;
import com.bbodeum.member.entity.Member;
import com.bbodeum.member.repository.MemberRepository;
@SpringBootTest
class ApplyRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 

	@Autowired
	private ApplyRepository ar;
	
	@Autowired
	private CourseRepository cr;
	@Autowired
	private DogRepository dr;
	@Autowired
	private MemberRepository mr;


	@Test
	void testSave() {
		Optional<Course> optC = cr.findById(3L);
		Course c = optC.get();
		
		Optional<Member> optM = mr.findById("lewis@goat.com");
		List<Dog> list = dr.findByMember(optM.get());
		Dog d = list.get(0);

		Apply a = new Apply(d, c, ApplyStatus.APPLIED);
		
		ar.save(a);
	}
	
	@Test
	void testFindById() {
		Optional<Course> optC = cr.findById(3L);
		Course c = optC.get();
		
		Optional<Member> optM = mr.findById("lewis@goat.com");
		List<Dog> list = dr.findByMember(optM.get());
		Dog d = list.get(0);
		
		ApplyId aID = new ApplyId(d.getDogId(),c.getCourseId());
		Optional<Apply> optA = ar.findById(aID);
		assertTrue(optA.isPresent());
		Apply a = optA.get();
		assertEquals("APPLIED", a.getApplyStatus().toString());
		logger.info("-- Apply findedById --");
		logger.info("id: " +a.getApplyId().getApplyDogId()+" + "+a.getApplyId().getApplyCourseId());
		logger.info("생성일: "+ a.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		logger.info("수정일: "+ a.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
	
	@Test
	void testFindByDog() {
		Optional<Dog> optD = dr.findById(2L);
		Dog d = optD.get();
		
		List<Apply> list = ar.findByDog(d);
		list.forEach((a)->{
			logger.info("-- 신청 반려견 이름: "+a.getDog().getDogName()+" --");
			logger.info("보호자: "+a.getDog().getMember().getMemName());
			logger.info("신청교육: "+a.getCourse().getCourseInfo().getCourseTitle());
			logger.info("교육일: "+a.getCourse().getCourseDate().toString());
			logger.info("신청일: "+a.getCreatedDate().toString());
		});
	}
	
	@Test
	void testFindByCourse() {
		Course c = cr.findById(3L).get();
		
		List<Apply> list = ar.findByCourse(c);
		list.forEach((a)->{
			logger.info("-- 신청교육 "+c.getCourseInfo().getCourseTitle()+" --");
			logger.info("교육일: "+a.getCourse().getCourseDate().toString());
			logger.info("신청일: "+a.getCreatedDate().toString());
			logger.info("보호자: "+a.getDog().getMember().getMemName());
			logger.info("반려견: "+a.getDog().getDogName());
		});
	}
}
