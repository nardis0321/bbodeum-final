package com.bbodeum.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends UserBase {
	
	private String phone;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date birthday;
	private int sex;
	private boolean marketing;

    @Builder
	public Member(Long userId, String id, String email, String password, 
    		String name, UserType userType, UserStatus userStatus,
    		String phone, Date birthday, int sex, boolean marketing) {
    	super(userId, id, email, password, name, userType, userStatus);
		this.phone = phone;
		this.birthday = birthday;
		this.sex = sex;
		this.marketing = marketing;
	}
}
