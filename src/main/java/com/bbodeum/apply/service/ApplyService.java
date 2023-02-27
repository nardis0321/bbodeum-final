package com.bbodeum.apply.service;

import java.util.List;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.entity.Dog;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

public interface ApplyService {
	public ApplyDTO getById(Long dogId, Long courseId) throws FindException;
	public List<ApplyDTO> getByDog(Dog d) throws FindException;
	public List<ApplyDTO> getByCourse(Course c) throws FindException;
	public void addApply(ApplyDTO dto) throws AddException;
	public void updateApply(ApplyDTO dto) throws ModifyException;
}
