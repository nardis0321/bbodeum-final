package com.bbodeum.trainer.control;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.trainer.dto.TrainerDTO;
import com.bbodeum.trainer.service.TrainerService;

@RestController
@RequestMapping("trainer/*")
public class TrainerController {
	@Autowired
	private TrainerService service;
	
	@PostMapping(value = "signin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signIn(@RequestBody Map<String,String> map, HttpSession session) throws FindException {
		TrainerDTO t = service.signIn(map.get("trId"),  map.get("trPwd"));
		session.setAttribute("logined", t.getTrId());
		return new ResponseEntity<>(t, HttpStatus.OK);
	}
	
	@GetMapping(value = "account/signup")
	public ResponseEntity<?> getSignUpId() throws FindException	{
		String nextId = service.getNextId();
		return new ResponseEntity<>(nextId, HttpStatus.OK);
	}
	
	@PostMapping(value = "account/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@RequestBody TrainerDTO dto) throws FindException, AddException	{
		service.signUp(dto);
		return new ResponseEntity<>(dto.getTrId(), HttpStatus.OK);
	}
}
