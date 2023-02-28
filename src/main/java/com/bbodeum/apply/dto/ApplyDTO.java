package com.bbodeum.apply.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import com.bbodeum.apply.entity.Apply;
import com.bbodeum.apply.entity.ApplyStatus;
import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.dog.dto.DogDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime createdDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime modifiedDate;
	
	public Apply toEntity(ApplyDTO dto) {
		Apply entity = Apply.builder()
				.dog(dto.getDog().toEntityOnlyWithId(dto.getDog()))
				.course(dto.getCourse().toEntityOnlyWithId(dto.getCourse()))
				.applyStatus(dto.getApplyStatus())
				.build();
		return entity;
	}
}
