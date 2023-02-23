package com.bbodeum.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.course.repository.CourseInfoRepository;

@SpringBootTest
class CourseInfoRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 

	@Autowired
	private CourseInfoRepository cIr;
	
//	@Test
	void testSaveCourseInfo() {
		CourseInfo cInfo = new CourseInfo(null, "퍼피 클래스", 
				"아기 강아지들을 위한 교육", "제일 좋아하는 간식", "6개월 미만의 반려견");
		cIr.save(cInfo);
	}
	
	@Test
	void testFindAll() {
		Iterable<CourseInfo> list = cIr.findAll();
		list.forEach((cInfo)->{
			logger.info("--- 교육 ---");
			logger.info("제목 : " + cInfo.getCourseTitle());
			logger.info("내용 : " + cInfo.getCourseContent());
			logger.info("준비물 : " + cInfo.getCoursePrep());
			logger.info("추천대상 : " + cInfo.getCourseRecomm());
		});
	}

}
