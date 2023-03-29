package com.bbodeum.dog.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.dog.service.DogService;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

@RestController
public class DogController {
	@Autowired
	private DogService service;
	
	@GetMapping(value="dog", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dogListByMem(HttpSession session) throws FindException {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) {
			List<DogDTO> list = service.getDogsByMem(logined);
			return new ResponseEntity<>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="dog/{dogId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dogById(@PathVariable("dogId") Long dogId, HttpSession session) throws FindException {
		DogDTO dog = service.getDog(dogId);
		return new ResponseEntity<>(dog, HttpStatus.OK);
	}

	@PostMapping(value="dogs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addDog(@RequestBody DogDTO dto, HttpSession session) throws AddException	{
		String logined = (String)session.getAttribute("logined");
		if(logined==null) {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		dto.setMember(logined);
		service.addDog(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping(value="dog", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editDog(@RequestBody DogDTO dto,  HttpSession session) throws ModifyException {
		String logined = (String)session.getAttribute("logined");
		if(logined==null) {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		dto.setMember(logined);
		service.updateDog(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("dog/{dogId}")
	public ResponseEntity<?> deleteDog(@PathVariable("dogId") Long dogId) throws ModifyException {
		service.deleteDog(dogId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
