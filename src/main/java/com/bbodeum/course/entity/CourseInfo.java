package com.bbodeum.course.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.bbodeum.course.dto.CourseInfoDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor 

@Entity
@DynamicInsert
@DynamicUpdate
public class CourseInfo {
	@Id
	@GeneratedValue
	private Long courseInfoId;
	private String courseTitle; //제목
	private String courseContent; //소개글
	private String coursePrep; //준비물
	private String courseRecomm; //추천대상
	
	@Builder
	public CourseInfo(Long courseInfoId, String courseTitle, String courseContent, String coursePrep,
			String courseRecomm) {
		super();
		this.courseInfoId = courseInfoId;
		this.courseTitle = courseTitle;
		this.courseContent = courseContent;
		this.coursePrep = coursePrep;
		this.courseRecomm = courseRecomm;
	}

	public CourseInfoDTO toDTO(CourseInfo entity) {
		CourseInfoDTO dto = CourseInfoDTO.builder()
				.courseInfoId(entity.getCourseInfoId())
				.courseTitle(entity.getCourseTitle())
				.courseContent(entity.getCourseContent())
				.coursePrep(entity.getCoursePrep())
				.courseRecomm(entity.getCourseRecomm())
				.build();
		return dto;
	}
}
