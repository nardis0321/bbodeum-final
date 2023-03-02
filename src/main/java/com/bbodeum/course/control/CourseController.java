package com.bbodeum.course.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbodeum.course.dto.CourseDTOLight;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.course.service.CourseService;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.trainer.dto.TrainerDTO;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("courses/*")
public class CourseController {
	@Autowired
	private CourseService service;
	
	@GetMapping(value="classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseList(int page) throws FindException {
		PageBean<CourseDTOLight> bean = service.getCourseAll(page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}
	
	@GetMapping(value="classes/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseList(@PathVariable("courseId") Long courseId) throws FindException {
		CourseDTOLight dto = service.getCourseById(courseId);
		return new ResponseEntity<>(dto, HttpStatus.OK); 
	}
	
	@GetMapping(value="info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseInfoList() throws FindException {
		List<CourseInfoDTO> list = service.getAllCourseInfo();
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}
	
	@PostMapping(value = "classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> write(HttpSession session,
			CourseDTOLight dto, MultipartFile f1) throws AddException, IOException{
		String logined = (String)session.getAttribute("logined");
		if(logined == null) {
			return new ResponseEntity<>("로그인이 안된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		dto.setTrainer(TrainerDTO.builder().trId(logined).build());

		String saveDirectory = "C:\\bbodeum\\attach";
		File saveDirFile = new File(saveDirectory);
		if(!saveDirFile.exists()) {
			saveDirFile.mkdir();
		}
		
		String newId = service.addCourse(dto).toString();

		if(f1 != null && f1.getSize() > 0) {  //원본+썸네일 저장
			if(f1.getSize()>1024*1024*5) {
				return new ResponseEntity<>("파일 용량이 너무 큽니다", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String[] f1NameArr = f1.getOriginalFilename().split("\\.");
			String f1Extension = f1NameArr[f1NameArr.length-1];
			String newF1Name = newId +"." + f1Extension;
			File file = new File(saveDirFile, newF1Name);
			FileCopyUtils.copy(f1.getBytes(), file);
			
			int width=300;
			int height=200;
			String thumbFileName = "t_" + newF1Name;
			File thumbFile = new File(saveDirFile, thumbFileName);
			FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);
			InputStream thumbnailIS = f1.getInputStream();				
			Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
			thumbnailIS.close();
		}
		return new ResponseEntity<>(newId, HttpStatus.OK);
	}
}