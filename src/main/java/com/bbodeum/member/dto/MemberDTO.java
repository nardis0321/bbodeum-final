package com.bbodeum.member.dto;

import com.bbodeum.member.entity.Member;
import com.bbodeum.member.entity.MemberStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
	private String memEmail;
	private String memPwd;	
	private String memName;
	private String memPhone;
	private MemberStatus memStatus;    
	
	public Member toEntity(MemberDTO dto) {
		Member m = Member.builder()
				.memEmail(dto.getMemEmail())
				.memPwd(dto.getMemPwd())
				.memName(dto.getMemName())
				.memPhone(dto.getMemPhone())
				.memStatus(dto.getMemStatus())
				.build();
		return m;
	}
}
