package com.bbodeum.member.control;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;
import com.bbodeum.member.service.MemberService;

@RestController
@RequestMapping("user/*")
public class MemberController {
	@Autowired
	private MemberService service;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping(value="account/check/{id}")
	public ResponseEntity<?> checkId(@PathVariable("id") String email) {
		Boolean flag = service.checkEmailExistence(email);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}

	@PostMapping(value = "account/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody MemberDTO dto) throws FindException, AddException	{
		service.signUp(dto);
		return new ResponseEntity<>(dto.getMemEmail(), HttpStatus.OK);
	}
	
	@PostMapping(value = "signin", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> signIn(@RequestBody Map mapString memEmail, @RequestBody String memPwd, HttpSession session) throws FindException {
	public ResponseEntity<?> signIn(@RequestBody Map<String,String> map, HttpSession session) throws FindException {
		MemberDTO m = service.signIn(map.get("memEmail"),  map.get("memPwd"));
		session.setAttribute("logined", m.getMemEmail());
		logger.info("로그인성공시 sessionid : " + session.getId());
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	
	@RequestMapping("signout")
	public String signOut(HttpSession session) {
		logger.info("로그아웃시 sessionid : " + session.getId());
		session.invalidate();
		return "";
	}
	
	@GetMapping(value="account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> info(HttpSession session) throws FindException {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) {
			MemberDTO m = service.getMemberInfo(logined);
			return new ResponseEntity<>(m, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="check", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checklogined(HttpSession session) {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) { //로그인된경우
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>("로그인이 안된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping(value = "account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editInfo(@RequestBody MemberDTO dto) throws ModifyException {
		service.updateMemberInfo(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
