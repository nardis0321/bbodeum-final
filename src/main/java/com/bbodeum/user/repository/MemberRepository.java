package com.bbodeum.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.bbodeum.user.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {}
