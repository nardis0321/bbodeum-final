package com.bbodeum.member.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
public class Member {
	@Id
	private String memEmail;

	private String memPwd;
	
	private String memName;
	
	private String memPhone;
	

	@Enumerated(EnumType.STRING)
	private MemberStatus memStatus;


	@Builder
	public Member(String memEmail, String memPwd, String memName, String memPhone, MemberStatus memStatus) {
		super();
		this.memEmail = memEmail;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memStatus = memStatus;
	}
		
//	@Temporal(TemporalType.DATE)
//	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
//	private Date birthday;
//	private int sex;
//	private boolean marketing;
    
}
