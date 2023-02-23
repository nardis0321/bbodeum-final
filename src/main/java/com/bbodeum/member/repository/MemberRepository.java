package com.bbodeum.member.repository;

import org.springframework.data.repository.CrudRepository;

import com.bbodeum.member.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
}

