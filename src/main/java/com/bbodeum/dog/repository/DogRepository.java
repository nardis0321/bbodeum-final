package com.bbodeum.dog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bbodeum.dog.entity.Dog;
import com.bbodeum.member.entity.Member;

public interface DogRepository extends CrudRepository<Dog, Long> {
	public List<Dog> findByMember(Member m);
	public List<Dog> findByMemberAndDogName(Member m, String dogName);
	
//	@Query("SELECT dogs FROM Dog dogs WHERE Member.memEmail = :memEmail")
//	public List<Dog> findDogByMemberId(@Param("memEmail") String memEmail);
	
	
//	@Query(value = "SELECT * FROM dog WHERE ", nativeQuery = true)
//	public List<Object[]> findDogByUser(String userId);

	
}
