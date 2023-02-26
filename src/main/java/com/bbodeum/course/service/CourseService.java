package com.bbodeum.course.service;

import java.util.List;

import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.exception.RemoveException;

public interface CourseService {
	public CourseInfoDTO getInfoCourseById(Long id) throws FindException;
	public List<CourseInfoDTO> getAllInfoCourse() throws FindException;
	public void addCourseInfo(CourseInfoDTO dto) throws AddException;
	public void updateInfoCourse(CourseInfoDTO dto) throws ModifyException;

	public CourseDTO getCourseById(Long id) throws FindException;
	public List<CourseDTO> getCourseByTrId(String trainerId) throws FindException;
	public void addCourse(CourseDTO dto) throws AddException;
    public void updateCourse(CourseDTO dto) throws ModifyException;
   
}
