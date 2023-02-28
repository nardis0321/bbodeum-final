package com.bbodeum.course.dto;

import java.util.Date;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.trainer.dto.TrainerDTO;
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
	@JsonFormat(pattern="yyyy년 MM월 dd일")
	private Date courseDate;
	private int coursePrice;
	private int courseVacancy;
	private CourseStatus courseStatus;
	
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
	
	public Course toEntityOnlyWithId(CourseDTOLight dto) {
		Course entity = Course.builder()
				.courseId(dto.getCourseId())
				.build();
		return entity;
	}
}
