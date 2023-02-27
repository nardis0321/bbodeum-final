package com.bbodeum.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.course.service.CourseService;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.trainer.dto.TrainerDTO;
import com.bbodeum.trainer.service.TrainerService;
@SpringBootTest
class CourseServiceTest {
	@Autowired
	CourseService cs;
	@Autowired
	TrainerService ts;

	@Test
	void testGetCourseInfo() {
		try {
			CourseInfoDTO dto = cs.getInfoCourseById(3L);
			System.out.println(dto);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetInfos() {
		try {
			List<CourseInfoDTO> list = cs.getAllInfoCourse();
			for(CourseInfoDTO dto : list) {
				System.out.println(dto);
			}
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddInfo() {
		CourseInfoDTO dto = CourseInfoDTO.builder()
				.courseTitle("산책 교육")
				.courseContent("반려견 행복의 기초, 산책 교육")
				.coursePrep("리드줄, 가장 좋아하는 간식")
				.courseRecomm("산책하는 법을 잘 모르는 반려견")
				.build();
		try {
			cs.addCourseInfo(dto);
		} catch (AddException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdateInfo() {
		CourseInfoDTO dto = CourseInfoDTO.builder()
				.courseInfoId(4L)
				.courseTitle("대형견 프리런")
				.courseContent("대형견들의 자유로운 질주")
				.courseRecomm("달리기를 좋아하는 대형견")
				.build();
		try {
			cs.updateInfoCourse(dto);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
	}
	
	//교육 테스트 ----
	@Test
	void testGetCourseById() {
		try {
			CourseDTO dto = cs.getCourseById(3L);
			System.out.println(dto);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testGetCourseByTrId() {
		try {
			List<CourseDTO> list = cs.getCourseByTrId("TR0000");
			list.forEach((dto)->{
				System.out.println(dto);	
			});
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAddCourse() {
		try {
			DateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
//			Date date = dateFormat.parse("25/03/2023 11:00");
			Date date = dateFormat.parse("25/04/2023 11:00");
			CourseInfoDTO infoDto = cs.getInfoCourseById(4L);
//			TrainerDTO trDto = ts.getTrainerInfo("TR0001");
			TrainerDTO trDto = ts.getTrainerInfo("TR0000");
			CourseDTO dto = CourseDTO.builder()
					.courseInfo(infoDto)
					.trainer(trDto)
					.courseLocation("운동장1")
					.courseDate(date)
					.coursePrice(20)
					.courseVacancy(10)
					.build();
				cs.addCourse(dto);
		} catch (AddException e) {
			e.printStackTrace();
		} catch (FindException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUpdateCourse() {
		try {
			
			CourseDTO dto = CourseDTO.builder()
					.courseId(41L)
					.courseStatus(CourseStatus.CANCELD)
					.build();
			cs.updateCourse(dto);
		} catch (ModifyException e) {
			e.printStackTrace();
		}
	}
}
