package com.bbodeum.course.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.trainer.entity.Trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTORequest {
	private Long courseId;
	private Long courseInfoId;
	private String trId;

	private String courseLocation;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date courseDate;
	private int coursePrice;
	private int courseVacancy;
	private CourseStatus courseStatus;
	
	public Course toEntity(CourseDTORequest dto){
		Course entity = Course.builder()
				.courseInfo(CourseInfo.builder().courseInfoId(dto.getCourseInfoId()).build())
				.trainer(Trainer.builder().trId(dto.getTrId()).build())
				.courseLocation(dto.getCourseLocation())
				.courseDate(dto.getCourseDate())
				.coursePrice(dto.getCoursePrice())
				.courseVacancy(dto.getCourseVacancy())
				.courseStatus(dto.getCourseStatus())
				.build();
		return entity;
	}
}
