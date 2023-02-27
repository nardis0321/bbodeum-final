package com.bbodeum.member.control;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;
import com.bbodeum.member.service.MemberService;

@RestController
@RequestMapping("member/*")
public class MemberController {
	@Autowired
	private MemberService service;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value="checklogined", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checklogined(HttpSession session) {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) { //로그인된경우
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>("로그인이 안된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "signIn", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signIn(String id, String pwd, HttpSession session) throws FindException	{
		MemberDTO m = service.signIn(id, pwd);
		session.setAttribute("logined", m.getMemEmail());
		logger.info("로그인성공시 sessionid : " + session.getId());
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	
	@RequestMapping("signOut")
	public String signOut(HttpSession session) {
		logger.info("로그아웃시 sessionid : " + session.getId());
		session.invalidate();
		return "";
	}
	
	@PutMapping(value = "signUp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody MemberDTO dto) throws FindException	{
		try {
			service.signUp(dto);
			return new ResponseEntity<>(dto.getMemEmail(), HttpStatus.OK);
		} catch (AddException e) {
			return new ResponseEntity<>("가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> info(HttpSession session) {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) {
			try {
				MemberDTO m = service.getMemberInfo(logined);
				return new ResponseEntity<>(m, HttpStatus.OK);
			} catch (FindException e) {
				e.printStackTrace();
				return new ResponseEntity<>("로드 실패", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "edit", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editInfo(@RequestBody MemberDTO dto) {
		try {
			
			dto.setMemEmail(null);
			service.updateMemberInfo(dto);
			return new ResponseEntity<>(dto.getMemEmail(), HttpStatus.OK);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>("수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
