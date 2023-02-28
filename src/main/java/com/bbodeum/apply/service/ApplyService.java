package com.bbodeum.apply.service;

import java.util.List;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

public interface ApplyService {
	public ApplyDTO getById(Long dogId, Long courseId) throws FindException;
	public List<ApplyDTO> getByDog(Long dogId) throws FindException;
	public List<ApplyDTO> getByCourse(Long courseId) throws FindException;
	public void addApply(Long dogId, Long courseId) throws AddException;
	public void dropApply(Long dogId, Long courseId) throws ModifyException;
}
