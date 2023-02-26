package com.bbodeum.course.dto;

import com.bbodeum.course.entity.CourseInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseInfoDTO {
	private Long courseInfoId;
	private String courseTitle; //제목
	private String courseContent; //소개글
	private String coursePrep; //준비물
	private String courseRecomm; //추천대상
	
	public CourseInfo toEntity(CourseInfoDTO dto) {
		CourseInfo entity = CourseInfo.builder()
				.courseInfoId(dto.getCourseInfoId())
				.courseTitle(dto.getCourseTitle())
				.courseContent(dto.getCourseContent())
				.coursePrep(dto.getCoursePrep())
				.courseRecomm(dto.getCourseRecomm())
				.build();
		return entity;
	}
}
