package com.bbodeum.course.repository;

import java.util.List;

import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.trainer.entity.Trainer;

public interface CourseRepositoryCustom {
	public List<Course> findByTrainerAndDay(Trainer trainer, Integer day);
	public List<Course> findByTrainer(Trainer trainer);
}
