package com.bbodeum.course.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.entity.Apply;
import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.trainer.dto.TrainerDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO {
	private Long courseId;
	private CourseInfoDTO courseInfo;
	private TrainerDTO trainer;
	
	private String courseLocation;
	private Date courseDate;
	private int coursePrice;

	private int courseVacancy;
	private List<ApplyDTO> apply;
	
	private CourseStatus courseStatus;
	
	public Course toEntity(CourseDTO dto) {
		List<Apply> list = new ArrayList<>();
		List<ApplyDTO> aDtoList = dto.getApply();
		if(aDtoList!=null) {
			aDtoList.forEach((aD)->{
				Apply ae = aD.toEntity(aD);
				list.add(ae);			
			});
		}
		Course entity = Course.builder()
//				.courseId(dto.getCourseId())
				.courseInfo(dto.getCourseInfo().toEntity(dto.courseInfo))
				.trainer(dto.getTrainer().toEntity(dto.getTrainer()))
				.courseLocation(dto.getCourseLocation())
				.courseDate(dto.getCourseDate())
				.coursePrice(dto.getCoursePrice())
				.courseVacancy(dto.getCourseVacancy())
				.apply(list)
				.courseStatus(dto.getCourseStatus())
				.build();
		return entity;
	}
	
}