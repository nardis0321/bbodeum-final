package com.bbodeum.apply.repository;

import org.springframework.stereotype.Repository;

import com.bbodeum.course.entity.QCourse;
import com.bbodeum.dog.entity.QDog;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ApplyRepositoryCustomImpl implements ApplyRepositoryCustom {
	 private final JPAQueryFactory query;
	 
	 QCourse c = QCourse.course;
	 QDog d = QDog.dog;
	 
	 public ApplyRepositoryCustomImpl(JPAQueryFactory query) {
		 this.query = query;
	 }

	@Override
	public int totalCntOfDog(Long dogId) {
		return query.selectFrom(d)
				.fetch().size();
	}

	@Override
	public int totalCntOfCourse(Long courseId) {
		return query.selectFrom(c)
				.fetch().size();
	}
}
