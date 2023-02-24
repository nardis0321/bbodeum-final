package com.bbodeum.member.service;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;

public interface MemberService {
	public MemberDTO signIn(String email, String pwd) throws FindException;
    public boolean checkEmailExistence(String email);
    public void signUp(MemberDTO dto) throws AddException;
    public MemberDTO getMemberInfo(String email) throws FindException;
    //이거 세 개 하나로 묶으면 안 되나?
    public void updateMemberInfo(MemberDTO dto) throws ModifyException;
    public void updateMemberPassword(MemberDTO dto) throws ModifyException;
    public void updateMemberStatus(MemberDTO dto) throws ModifyException;
}
