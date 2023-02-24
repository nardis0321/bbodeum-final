package com.bbodeum.apply.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bbodeum.basetime.entity.BaseTimeEntity;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.entity.Dog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name = "apply")
public class Apply extends BaseTimeEntity {
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

	@Enumerated(EnumType.STRING)
	private ApplyStatus applyStatus;

	@Builder
	public Apply(Dog dog, Course course, ApplyStatus applyStatus) {
		super();
		this.dog = dog;
		this.course = course;
		this.applyStatus = applyStatus;
	}

	
}
