package com.bbodeum.apply.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.basetime.entity.BaseTimeEntity;
import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.dog.entity.Dog;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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
		this.applyId = new ApplyId(dog.getDogId(), course.getCourseId());
		this.dog = dog;
		this.course = course;
		this.applyStatus = applyStatus;
	}
	
	public ApplyDTO toDTO(Apply entity) {
		Dog dEntity = entity.getDog();
		Course cEntity = entity.getCourse();
		ApplyDTO dto = ApplyDTO.builder()
				.dog(dEntity.toDTO(dEntity))
//				.course(cEntity.toDTO(cEntity))
				.course(cEntity.toDTONoApplies(cEntity))
				.applyStatus(entity.getApplyStatus())
				.build();
		return dto;
	}
}
