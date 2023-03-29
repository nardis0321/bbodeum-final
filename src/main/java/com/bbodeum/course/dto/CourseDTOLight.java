package com.bbodeum.course.dto;

import java.util.Date;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.trainer.dto.TrainerDTO;
import com.bbodeum.trainer.entity.Trainer;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTOLight {
	private Long courseId;
	private CourseInfoDTO courseInfo;
	private TrainerDTO trainer;
	
	private String courseLocation;
	@JsonFormat(pattern="MM월 dd일 HH시", timezone = "Asia/Seoul")
	private Date courseDate;
	private int coursePrice;
	private int courseVacancy;
	private CourseStatus courseStatus;
	
	private int applyCnt;
	
	public Course toEntity(CourseDTOLight dto) {
		Course entity = Course.builder()
				.courseInfo(dto.getCourseInfo().toEntity(dto.getCourseInfo()))
				.trainer(dto.getTrainer().toEntity(dto.getTrainer()))
				.courseLocation(dto.getCourseLocation())
				.courseDate(dto.getCourseDate())
				.coursePrice(dto.getCoursePrice())
				.courseVacancy(dto.getCourseVacancy())
				.courseStatus(dto.getCourseStatus())
				.build();
		return entity;
	}

	public Course toEntityWithIds(CourseDTOLight dto) {
		Course entity = Course.builder()
				.courseInfo(CourseInfo.builder().courseInfoId(dto.getCourseInfo().getCourseInfoId()).build())
				.trainer(Trainer.builder().trId(dto.getTrainer().getTrId()).build())
				.courseLocation(dto.getCourseLocation())
				.courseDate(dto.getCourseDate())
				.coursePrice(dto.getCoursePrice())
				.courseVacancy(dto.getCourseVacancy())
				.courseStatus(dto.getCourseStatus())
				.build();
		return entity;
	}

}
