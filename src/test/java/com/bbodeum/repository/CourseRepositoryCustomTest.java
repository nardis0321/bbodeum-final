package com.bbodeum.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.course.entity.QCourse;
import com.bbodeum.course.repository.CourseInfoRepository;
import com.bbodeum.course.repository.CourseRepositoryCustom;
import com.bbodeum.trainer.entity.QTrainer;
import com.bbodeum.trainer.entity.Trainer;
import com.bbodeum.trainer.repository.TrainerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
@SpringBootTest
class CourseRepositoryCustomTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 

	@Autowired
	private CourseRepositoryCustom cr;
	
	@Autowired
	private TrainerRepository tr;
	
	@Test
	void testFindByTrainer() {
		Optional<Trainer> optT = tr.findById("TR0000");
		assertNotNull(optT);

		List<Course> list = cr.findByTrainer(optT.get());
		list.forEach((c)->{
			logger.info("-- "+c.getTrainer()+" --");
			logger.info("교육제목: "+ c.getCourseInfo().getCourseTitle());
			logger.info("장소: " + c.getCourseLocation());
			logger.info("날짜: " + c.getCourseDate().toString());
			logger.info("가격: " + c.getCoursePrice());
			logger.info("남은 모집인원: " + c.getCourseVacancy());
			logger.info("상태: "+c.getCourseStatus());
		});
	}
	
	@Test
	void testFindByTrainerAndDay() {	
		Optional<Trainer> optT = tr.findById("TR0000");
		assertNotNull(optT);
		
		List<Course> list = cr.findByTrainerAndDay(optT.get(), 1);
		list.forEach((c)->{
			logger.info("-- "+c.getTrainer()+" --");
			logger.info("교육제목: "+ c.getCourseInfo().getCourseTitle());
			logger.info("장소: " + c.getCourseLocation());
			logger.info("날짜: " + c.getCourseDate().toString());
			logger.info("가격: " + c.getCoursePrice());
			logger.info("남은 모집인원: " + c.getCourseVacancy());
			logger.info("상태: "+c.getCourseStatus());
		});
	}
	
}
