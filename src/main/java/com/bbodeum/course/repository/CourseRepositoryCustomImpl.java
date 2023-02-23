package com.bbodeum.course.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.course.entity.QCourse;
import com.bbodeum.trainer.entity.QTrainer;
import com.bbodeum.trainer.entity.Trainer;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Repository
public class CourseRepositoryCustomImpl implements CourseRepositoryCustom {
	 private final JPAQueryFactory query;
	 QCourse c = QCourse.course;
	 QTrainer t = QTrainer.trainer;
	 
	 public CourseRepositoryCustomImpl(JPAQueryFactory query) {
		 this.query = query;
	 }

	@Override
	public List<Course> findByTrainer(Trainer trainer) {
		return query.selectFrom(c)
				.join(c.trainer, t)
				.where(t.trId.eq(trainer.getTrId()))
				.fetch();
	}

	 @Override
//	 public List<Course> findByTrainer(Trainer trainer, Integer day, Pageable pageable) {
	 public List<Course> findByTrainerAndDay(Trainer trainer, Integer day) {
		 StringTemplate test1 = Expressions.stringTemplate("to_char({0}, {1})", c.courseDate,ConstantImpl.create("dy"));
		 DateTemplate<Date> test2 = Expressions.dateTemplate(Date.class,"to_char({0}, {1})", c.courseDate,ConstantImpl.create("dy"));
		 System.out.println(test1);
		 System.out.println(test2);
		return query.selectFrom(c)
						.join(c.trainer, t)
						.where(
								t.trId.eq(trainer.getTrId()),
								c.courseDate.dayOfWeek().eq(day)
						)
					.orderBy(c.courseDate.desc())
					.fetch();
	}

	 private BooleanExpression trEq(Trainer trainer){
			return trainer != null ? t.trId.eq(trainer.getTrId()) : null;
		}
	private BooleanExpression dayEq(Integer day){
		//1-7 : sun-sat
		return day != null ? c.courseDate.dayOfWeek().eq(day) : null;
	}	 
}
