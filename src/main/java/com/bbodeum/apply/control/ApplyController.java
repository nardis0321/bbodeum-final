package com.bbodeum.apply.control;

import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.service.ApplyService;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

@RestController
@RequestMapping("applies/*")
public class ApplyController {
	@Autowired
	private ApplyService service;
	
	@GetMapping(value="dogs/{dogId}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> appliesByDog(@PathVariable("dogId") Long dogId,@PathVariable("page") int page) throws FindException {
		PageBean<ApplyDTO> bean = service.getByDog(dogId, page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}

	@GetMapping(value="classes/{courseId}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> appliesByCourse(@PathVariable("courseId") Long courseId,@PathVariable("page") int page) throws FindException {
		PageBean<ApplyDTO> bean = service.getByCourse(courseId, page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}
	
	@PostMapping(value="classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addApply(@RequestBody Map<String, String> map, HttpSession session) throws AddException {
		String logined = (String)session.getAttribute("logined");
		if(logined==null) {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Long d = Long.parseLong(map.get("d"));
		Long c = Long.parseLong(map.get("c"));
		try {
			service.getById(d, c);
			return new ResponseEntity<>("이미 신청한 교육입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (FindException e) {
			service.addApply(d, c, map.get("iu"), map.get("mu"));
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PatchMapping(value="classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cancleApply(@RequestBody Map<String, Long> map, HttpSession session) throws ModifyException	{
		String logined = (String)session.getAttribute("logined");
		if(logined==null) {
			return new ResponseEntity<>("로그인이 안 된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		service.dropApply(map.get("d"), map.get("c"));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
