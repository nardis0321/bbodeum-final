package com.bbodeum.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.course.repository.CourseInfoRepository;
import com.bbodeum.course.repository.CourseRepository;
import com.bbodeum.trainer.entity.Trainer;
import com.bbodeum.trainer.repository.TrainerRepository;
@SpringBootTest
class CourseRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 

	@Autowired
	private CourseRepository cr;
	
	@Autowired
	private CourseInfoRepository cIr;
	@Autowired
	private TrainerRepository tr;
	
	@Test
	void testSave() throws ParseException {
		Optional<CourseInfo> optCI = cIr.findById(1L);
		assertNotNull(optCI);
		Optional<Trainer> optT = tr.findById("TR0000");
		assertTrue(optT.isPresent());

		String dateStr = "2022년 03월 19일 15시 00분";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		Date cday = formatter.parse(dateStr);
		
		Course c = new Course(null, optCI.get(), optT.get(),
				"102호", cday,
				20, 15,				
				CourseStatus.RECRUITING, null);
		cr.save(c);

	}
	
	@Test
	void testFindByTrId(){
		List<Course> list = cr.findByTrId("TR0000");
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

	
	@Test
	void testFindByCourseInfo() {
		Optional<CourseInfo> optCI = cIr.findById(4L);
		assertNotNull(optCI);
		
		List<Course> list = cr.findByCourseInfo(optCI.get());
		list.forEach((c)->{
			logger.info("--- course ---");
			logger.info("-- "+c.getCourseInfo()+" --");
			logger.info("교육자: "+ c.getTrainer().getTrName());
			logger.info("장소: " + c.getCourseLocation());
			logger.info("날짜: " + c.getCourseDate().toString());
			logger.info("가격: " + c.getCoursePrice());
			logger.info("남은 모집인원: " + c.getCourseVacancy());
			logger.info("상태: "+c.getCourseStatus());
		});
	}
	
	@Test
	void testFindById() {
		Optional<Course> optC = cr.findById(7L);
		assertTrue(optC.isPresent());
		
		Course c = optC.get();
	}
	
	@Test
	void testUpdate() throws ParseException {
		Optional<Course> optC = cr.findById(7L);
		assertTrue(optC.isPresent());
		
		String dateStr = "2022-02-19 15:00";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd T HH:mm");
		Date cday = formatter.parse(dateStr);

		Course c = optC.get();
//		c.setCourseDate(cday);
		cr.save(c);
	}

}
