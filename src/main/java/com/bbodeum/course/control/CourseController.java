package com.bbodeum.course.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbodeum.course.dto.CourseDTOLight;
import com.bbodeum.course.dto.CourseDTORequest;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.course.service.CourseService;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("courses/*")
public class CourseController {
	@Autowired
	private CourseService service;
	
	@GetMapping(value="classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseList(int page, HttpServletResponse response) throws FindException{ //, FileNotFoundException {
		PageBean<CourseDTOLight> bean = service.getCourseAll(page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}
	
	@GetMapping(value="classes/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseDetail(@PathVariable("courseId") Long courseId) throws FindException {
		CourseDTOLight dto = service.getCourseById(courseId);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "classes/{courseId}/img", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseDetailImg(@PathVariable("courseId") Long courseId) throws FindException {
		String saveDirectory = "C:\\bbodeum\\attach";
		String fileName = "t_" + courseId.toString()+ ".jpg";
		File file = new File(saveDirectory, fileName);
		if (!file.exists()) {
			throw new FindException("교육 이미지가 없습니다");
		}
		try {
			HttpHeaders headers = new HttpHeaders();
			String contentType = Files.probeContentType(file.toPath());
			headers.add(HttpHeaders.CONTENT_TYPE, contentType);
			headers.add(HttpHeaders.CONTENT_LENGTH, "" + file.length());
			headers.add(HttpHeaders.CONTENT_DISPOSITION,
					"inline;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));

			byte[] bArr = FileCopyUtils.copyToByteArray(file);
			return new ResponseEntity<>(bArr, headers, HttpStatus.OK);
		} catch (Exception e) {
			throw new FindException("교육 이미지 처리에 실패했습니다");
		}
	}
	
	@GetMapping(value="info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseInfoList() throws FindException {
		List<CourseInfoDTO> list = service.getAllCourseInfo();
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}
	
	@PostMapping(value = "classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> write(HttpSession session,
			CourseDTORequest dto, MultipartFile f) throws AddException, IOException{
		//로그인 체크 후 course 정보 저장
		String logined = (String)session.getAttribute("logined");
		if(logined == null) {
			return new ResponseEntity<>("로그인이 안된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		dto.setTrId(logined);
		String newId = service.addCourse(dto).toString();

		//저장된 id값으로 이미지 저장
		String saveDirectory = "C:\\bbodeum\\attach";
		File saveDirFile = new File(saveDirectory);
		if(!saveDirFile.exists()) {
			saveDirFile.mkdir();
		}
		if(f != null && f.getSize() > 0) {
			if(f.getSize()>1024*1024*70) {
				return new ResponseEntity<>("파일 용량이 너무 큽니다", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String[] f1NameArr = f.getOriginalFilename().split("\\.");
			String f1Extension = f1NameArr[f1NameArr.length-1];
//			String newF1Name = newId +"." + f1Extension;
			String newF1Name = newId +"." + "jpg";
			File file = new File(saveDirFile, newF1Name);
			FileCopyUtils.copy(f.getBytes(), file);
			
			//썸네일 저장
			int width=300;
			int height=200;
			String thumbFileName = "t_" + newF1Name;
			File thumbFile = new File(saveDirFile, thumbFileName);
			FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);
			InputStream thumbnailIS = f.getInputStream();				
			Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
			thumbnailIS.close();
		}
		return new ResponseEntity<>(newId, HttpStatus.OK);
	}
}