package com.bbodeum.course.entity;

import java.util.ArrayList;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.entity.Apply;
import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.dto.CourseDTOLight;
import com.bbodeum.trainer.dto.TrainerDTO;
import com.bbodeum.trainer.entity.Trainer;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
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
	
	
	@Builder
	public Course(Long courseId, CourseInfo courseInfo, Trainer trainer, String courseLocation, Date courseDate,
			int coursePrice, int courseVacancy, CourseStatus courseStatus, List<Apply> apply) {
		this.courseId = courseId;
		this.courseInfo = courseInfo;
		this.trainer = trainer;
		this.courseLocation = courseLocation;
		this.courseDate = courseDate;
		this.coursePrice = coursePrice;
		this.courseVacancy = courseVacancy;
		this.courseStatus = courseStatus;
		this.apply = apply;
	}
	
	
	public CourseDTO toDTO(Course entity) {
		List<ApplyDTO> aDtoList = new ArrayList<>();
		List<Apply> list = entity.getApply();
		list.forEach((ae)->{
			ApplyDTO aD = ae.toDTO(ae);
			aDtoList.add(aD);
		});
		CourseDTO dto = CourseDTO.builder()
				.courseId(entity.getCourseId())
				.courseInfo(entity.getCourseInfo().toDTO(entity.getCourseInfo()))
				.trainer(entity.getTrainer().toDTOWithoutPwd(entity.getTrainer()))
				.courseLocation(entity.getCourseLocation())
				.courseDate(entity.getCourseDate())
				.coursePrice(entity.getCoursePrice())
				.courseVacancy(entity.getCourseVacancy())
				.apply(aDtoList)
				.courseStatus(entity.getCourseStatus())
				.build();
		return dto;
	}

	public CourseDTOLight toDTOLight(Course entity) {
		List<Apply> list = entity.getApply();
		int listSize = list.size();
		TrainerDTO t = entity.getTrainer().toDTOWithoutPwd(trainer);
		t.setTrId("");
		t.setTrStatus(null);
		CourseDTOLight dto = CourseDTOLight.builder()
				.courseId(entity.getCourseId())
				.courseInfo(entity.getCourseInfo().toDTO(entity.getCourseInfo()))
				.trainer(t)
				.courseLocation(entity.getCourseLocation())
				.courseDate(entity.getCourseDate())
				.coursePrice(entity.getCoursePrice())
				.courseVacancy(entity.getCourseVacancy())
				.courseStatus(entity.getCourseStatus())
				.applyCnt(listSize)
				.build();
		return dto;
	}
	
	public CourseDTO toDTONoApplies(Course entity) {
		CourseDTO dto = CourseDTO.builder()
				.courseId(entity.getCourseId())
				.courseInfo(entity.getCourseInfo().toDTO(entity.getCourseInfo()))
				.trainer(entity.getTrainer().toDTOWithoutPwd(entity.getTrainer()))
				.courseLocation(entity.getCourseLocation())
				.courseDate(entity.getCourseDate())
				.coursePrice(entity.getCoursePrice())
				.courseVacancy(entity.getCourseVacancy())
				.courseStatus(entity.getCourseStatus())
				.build();
		return dto;
	}
}
