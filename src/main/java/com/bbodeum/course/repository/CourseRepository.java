package com.bbodeum.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;

public interface CourseRepository extends CrudRepository<Course, Long>, CourseRepositoryCustom {
	public List<Course> findByCourseInfo(CourseInfo courseInfo);
	
}
