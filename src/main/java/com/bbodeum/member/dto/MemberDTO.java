package com.bbodeum.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
	private String memEmail;
    
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
	private String memPwd;
    
    @NotBlank(message = "이름을 입력해주세요.")
	private String memName;
	
    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(regexp = "(01[016789])[-](\\d{3,4})[-](\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
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