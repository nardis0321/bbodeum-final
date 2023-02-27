package com.bbodeum.apply.dto;

import com.bbodeum.apply.entity.Apply;
import com.bbodeum.apply.entity.ApplyStatus;
import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.dog.dto.DogDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplyDTO {
	private DogDTO dog;
	private CourseDTO course;
	private ApplyStatus applyStatus;
	
	public Apply toEntity(ApplyDTO dto) {
		Apply entity = Apply.builder()
				.dog(dto.getDog().toEntityOnlyWithId(dto.getDog()))
				.course(dto.getCourse().toEntityOnlyWithId(dto.getCourse()))
				.applyStatus(dto.getApplyStatus())
				.build();
		return entity;
	}
}
