package com.bbodeum.user.entity;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Trainer extends UserBase {
	private String summary;

	private String skills;

	private String education;

	private String experience;

	private String certificates;

	@Builder
    public Trainer(Long userId, String id, String email, String password, 
    		String name, String phone, UserType userType, UserStatus userStatus,
    		String summary, String skills, String education, String experience, String certificates) {
        super(userId, id, email, password, name, userType, userStatus);
		this.summary = summary;
		this.skills = skills;
		this.education = education;
		this.experience = experience;
		this.certificates = certificates;
	}
}
