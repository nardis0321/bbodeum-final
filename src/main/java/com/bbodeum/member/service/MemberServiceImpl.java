package com.bbodeum.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;
import com.bbodeum.member.entity.Member;
import com.bbodeum.member.entity.MemberStatus;
import com.bbodeum.member.repository.MemberRepository;

//@로그처리 필요한가?
//@RequiredArgsConstructor 필요한가?
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepository mR;
	
	@Override
	public MemberDTO signIn(String email, String pwd) throws FindException {
		Optional<Member> optM = mR.findById(email);
		if(optM.isPresent()) {
			Member m = optM.get();
			if(m.getMemPwd().equals(pwd)) {
				MemberDTO member = MemberDTO.builder()
						.memEmail(email)
						.memPwd("PASSWORD")
						.memName(optM.get().getMemName())
						.memPhone(optM.get().getMemPhone())
						.memStatus(optM.get().getMemStatus())
						.build();
				return member;
			} else {
				throw new FindException("로그인 실패");			
			}
		}else {
			throw new FindException("로그인 실패");			
		}
	}

	@Override
	public boolean checkEmailExistence(String email) {
		Optional<Member> optM = mR.findById(email);
		if(optM.isPresent()) {
			return true;
		}			
		return false;
	}

	@Override
	public void signUp(MemberDTO dto) throws AddException {
		try {			
		Member m = dto.toEntity(dto); 
		mR.save(m);
		} catch(Exception e) {
			throw new AddException("가입 실패");
		}
	}

	@Override
	public MemberDTO getMemberInfo(String email) throws FindException {
		try {	
			Optional<Member> optM = mR.findById(email);
			if(optM.isPresent()) {
				MemberDTO dto = MemberDTO.builder()
						.memEmail(email)
						.memPwd("PASSWORD")
						.memName(optM.get().getMemName())
						.memPhone(optM.get().getMemPhone())
						.memStatus(optM.get().getMemStatus())
						.build();
				return dto;
			} else {
				throw new FindException("아이디에 해당하는 고객이 없습니다");
			}
		} catch(Exception e) {
			throw new FindException("아이디에 해당하는 고객이 없습니다");
		}
	}

	@Override
	public void updateMemberInfo(MemberDTO dto) throws ModifyException {
		try {
			String email = dto.getMemEmail();
			Optional<Member> optM = mR.findById(email);
			if(optM.isPresent()) {
				String pwd = dto.getMemPwd();
				String name = dto.getMemName();
				String phone = dto.getMemPhone();
				MemberStatus memSt = dto.getMemStatus();
				
				//dynamic update 위해 null값 채우기
				Member m = optM.get(); 
				pwd = (pwd == null) ? m.getMemPwd() : pwd;
				name = (name == null) ? m.getMemName() : name;
				phone = (phone == null) ? m.getMemPhone() : phone;
				memSt = (memSt == null) ? m.getMemStatus() : memSt;
				m = null;
				
				m = Member.builder()
						.memEmail(email)
						.memPwd(pwd)
						.memName(name)
						.memPhone(phone)
						.memStatus(memSt)
						.build();
				mR.save(m);
			}
		} catch(Exception e) {
			throw new ModifyException("정보 수정에 실패했습니다");
		}
	}

}
