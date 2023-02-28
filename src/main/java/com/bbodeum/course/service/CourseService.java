package com.bbodeum.course.service;

import java.util.List;

import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.dto.CourseDTOLight;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

public interface CourseService {
	public CourseInfoDTO getInfoCourseById(Long id) throws FindException;
	public List<CourseInfoDTO> getAllCourseInfo() throws FindException;
	public void addCourseInfo(CourseInfoDTO dto) throws AddException;
	public void updateInfoCourse(CourseInfoDTO dto) throws ModifyException;

//	public CourseDTO getCourseById(Long id) throws FindException;
	public CourseDTOLight getCourseById(Long id) throws FindException;
	public List<CourseDTO> getCourseByTrId(String trainerId) throws FindException;
	public PageBean<CourseDTOLight> getCourseAll(int curPage) throws FindException;
	public void addCourse(CourseDTO dto) throws AddException;
    public void updateCourse(CourseDTO dto) throws ModifyException;
   
}
