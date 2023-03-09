package com.bbodeum.apply.service;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

public interface ApplyService {
	public ApplyDTO getById(Long dogId, Long courseId) throws FindException;
	public PageBean<ApplyDTO> getByDog(Long dogId, int curPage) throws FindException;
	public PageBean<ApplyDTO> getByCourse(Long courseId, int curPage) throws FindException;
	public void addApply(Long dogId, Long courseId, String impUid, String merchantUid) throws AddException;
	public void dropApply(Long dogId, Long courseId) throws ModifyException;
}
