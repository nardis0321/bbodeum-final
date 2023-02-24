package com.bbodeum.apply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbodeum.apply.entity.Apply;
import com.bbodeum.apply.entity.ApplyId;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.entity.Dog;

public interface ApplyRepository extends JpaRepository<Apply, ApplyId> {
	public List<Apply> findByDog(Dog d);
	public List<Apply> findByCourse(Course c);
}
