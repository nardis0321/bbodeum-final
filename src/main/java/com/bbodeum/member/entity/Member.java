package com.bbodeum.member.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.bbodeum.member.dto.MemberDTO;

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

	public MemberDTO toDTO(Member entity) {
		MemberDTO dto = MemberDTO.builder()
				.memEmail(entity.getMemEmail())
				.memPwd(entity.getMemPwd())
				.memName(entity.getMemName())
				.memPhone(entity.getMemPhone())
				.memStatus(entity.getMemStatus())
				.build();
		return dto;
	}
	
	@Builder
	public Member(String memEmail, String memPwd, String memName, String memPhone, MemberStatus memStatus) {
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
