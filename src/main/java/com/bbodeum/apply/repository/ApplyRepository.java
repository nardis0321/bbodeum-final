package com.bbodeum.apply.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bbodeum.apply.entity.Apply;
import com.bbodeum.apply.entity.ApplyId;
import com.bbodeum.apply.entity.ApplyStatus;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.entity.Dog;

public interface ApplyRepository extends JpaRepository<Apply, ApplyId> {
	public List<Apply> findByDog(Dog d);
	public List<Apply> findByCourse(Course c);
}
