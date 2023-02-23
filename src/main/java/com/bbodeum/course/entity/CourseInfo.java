package com.bbodeum.course.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString

@Entity
@Table(name="course_info")
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
	
}
