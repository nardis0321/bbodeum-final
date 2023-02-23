package com.bbodeum.apply.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bbodeum.basetime.entity.BaseTimeEntity;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.entity.Dog;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name = "apply")
public class Apply extends BaseTimeEntity{
	@EmbeddedId
	private ApplyId applyId = new ApplyId();

	@MapsId("applyDogId")
	@ManyToOne
	@JoinColumn(name="apply_dog_id")
	private Dog dog;

	@MapsId("applyCourseId")
	@ManyToOne
	@JoinColumn(name="apply_course_id")
	private Course course;

	private int applyStatus;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
//	private Date applyDate;

}
