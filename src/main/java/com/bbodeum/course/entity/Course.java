package com.bbodeum.course.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.bbodeum.apply.entity.Apply;
import com.bbodeum.trainer.entity.Trainer;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table
@DynamicUpdate
public class Course {
	@Id
	@GeneratedValue
	private Long courseId;
	
	@ManyToOne
	@JoinColumn(name= "course_course_info_id", nullable = false)
	private CourseInfo courseInfo;

	@ManyToOne
	@JoinColumn(name= "course_tr_Id", nullable = false)
	private Trainer trainer; //개설자
	
	private String courseLocation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
	private Date courseDate;
	
	private int coursePrice;
	
	private int courseVacancy;
	
	@Enumerated(EnumType.STRING)
	private CourseStatus courseStatus;

	@OneToMany(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			mappedBy = "course")
	private List<Apply> apply;

}
