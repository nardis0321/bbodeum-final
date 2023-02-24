package com.bbodeum.course.repository;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.repository.CrudRepository;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.trainer.entity.Trainer;
import com.querydsl.jpa.impl.JPAQueryFactory;

public interface CourseRepository extends CrudRepository<Course, Long>, CourseRepositoryCustom {
	public List<Course> findByCourseInfo(CourseInfo courseInfo);
//	@Query("SELECT dogs FROM Dog dogs WHERE Member.memEmail = :memEmail")
//	public List<Dog> findDogByMemberId(@Param("memEmail") String memEmail);

}
