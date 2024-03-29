package com.bbodeum.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.dto.CourseDTOLight;
import com.bbodeum.course.dto.CourseDTORequest;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.course.entity.Course;
import com.bbodeum.course.entity.CourseInfo;
import com.bbodeum.course.entity.CourseStatus;
import com.bbodeum.course.repository.CourseInfoRepository;
import com.bbodeum.course.repository.CourseRepository;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.trainer.dto.TrainerDTO;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository cr;
	@Autowired
	private CourseInfoRepository cir;
//	private static final int CNT_PER_PAGE = 12;

	//--- 교육정보 ---
	@Override
	public CourseInfoDTO getInfoCourseById(Long id) throws FindException {
		Optional<CourseInfo> optCI = cir.findById(id);
		if(optCI.isPresent()) {
			CourseInfo entity = optCI.get();
			CourseInfoDTO dto = entity.toDTO(entity);
			return dto;
		} else {
			throw new FindException("교육 정보를 찾을 수 없습니다");
		}
	}

	@Override
	public List<CourseInfoDTO> getAllCourseInfo() throws FindException {
		Iterable<CourseInfo> iter = cir.findAll();
		List<CourseInfoDTO> list = new ArrayList<CourseInfoDTO>();
		iter.forEach((entity)->{
			CourseInfoDTO dto = entity.toDTO(entity);
			list.add(dto);
		});
		return list;
	}

	@Override
	public void addCourseInfo(CourseInfoDTO dto) throws AddException {
		CourseInfo entity = dto.toEntity(dto);
		cir.save(entity);
	}

	@Override
	public void updateInfoCourse(CourseInfoDTO dto) throws ModifyException {
		Long id = dto.getCourseInfoId();
		Optional<CourseInfo> optC = cir.findById(id);
		if(optC.isPresent()) {
			String title = dto.getCourseTitle();
			String content = dto.getCourseContent();
			String prep = dto.getCoursePrep();
			String recomm = dto.getCourseRecomm();

			//dynamic update 위해 null값 채우기
			CourseInfo entity = optC.get(); 
			title = (title==null) ? entity.getCourseTitle() : title;
			content = (content==null) ? entity.getCourseContent() : content;
			prep = (prep==null) ? entity.getCoursePrep() : prep;
			recomm = (recomm==null) ? entity.getCourseRecomm() : recomm;
			entity = null;
			
			entity = CourseInfo.builder()
					.courseInfoId(id)
					.courseTitle(title)
					.courseContent(content)
					.coursePrep(prep)
					.courseRecomm(recomm)
					.build();
			cir.save(entity);
		}
		
	}
	
	//--- 실제 교육들 ---
	@Override
	@Transactional
	public CourseDTOLight getCourseById(Long id) throws FindException {
		Optional<Course> optC = cr.findById(id);
		if(optC.isPresent()) {
			Course entity = optC.get();
			CourseDTOLight dto = entity.toDTOLight(entity);
			return dto;
		} else {
			throw new FindException("교육 정보를 찾을 수 없습니다");
		}
	}

	@Override
	@Transactional
	public List<CourseDTO> getCourseByTrId(String trId) throws FindException {
		List<Course> list = cr.findByTrId(trId);
		List<CourseDTO> dtoList = new ArrayList<CourseDTO>();;
		list.forEach((entity)->{
			CourseDTO dto = entity.toDTO(entity);
			dtoList.add(dto);
		});
		return dtoList;
	}

	@Override
	public Long addCourse(CourseDTORequest dto) throws AddException {
		dto.setCourseStatus(CourseStatus.RECRUITING);
		Course entity = dto.toEntity(dto);
		return cr.save(entity).getCourseId();
	}

	@Override
	public void updateCourse(CourseDTO dto) throws ModifyException {
		Long id = dto.getCourseId();
		Optional<Course> optC = cr.findById(id);
		if(optC.isPresent()) {
			CourseInfoDTO infoDTO = dto.getCourseInfo();
			TrainerDTO trDTO = dto.getTrainer();
			String location = dto.getCourseLocation();
			Date date = dto.getCourseDate();
			Integer price = dto.getCoursePrice();
			Integer vacancy = dto.getCourseVacancy();
			CourseStatus stat = dto.getCourseStatus();

			//dynamic update 위해 null값 채우기
			Course entity = optC.get(); 
			infoDTO = (infoDTO==null)? entity.getCourseInfo().toDTO(entity.getCourseInfo()) : infoDTO;
			trDTO = (trDTO==null)? entity.getTrainer().toDTOWithoutPwd(entity.getTrainer()) : trDTO;
			location = (location==null)? entity.getCourseLocation() : location;
			date = (date==null)? entity.getCourseDate() : date;
			price = (price==null)? entity.getCoursePrice() : price;
			vacancy = (vacancy==null)? entity.getCourseVacancy() : vacancy;
			stat = (stat==null)? entity.getCourseStatus() : stat;
			entity = null;
			
			entity = Course.builder()
					.courseId(id)
					.courseInfo(infoDTO.toEntity(infoDTO))
					.trainer(trDTO.toEntity(trDTO))
					.courseLocation(location)
					.courseDate(date)
					.coursePrice(price)
					.courseVacancy(vacancy)
					.courseStatus(stat)
					.build();
			cr.save(entity);
		} else {
			throw new ModifyException("교육 수정에 실패했습니다");
		}
		
	}

	@Override
	public PageBean<CourseDTOLight> getCourseAll(int curPage) throws FindException {
		Long limit = 12L;  //한 페이지 안의 목록 개수
		Long offset = limit*(curPage-1); //페이지 별 목록 시작점, 쿼리가 +1 해줌

		Iterable<Course> iter = cr.findAllOrderedPaged(limit, offset);

		List<CourseDTOLight> list = new ArrayList<>();
		iter.forEach((c)->{
			CourseDTOLight dto = c.toDTOLight(c);
			list.add(dto);
		});
		
		int totalCnt = cr.totalCnt();
		PageBean<CourseDTOLight> bean = new PageBean<CourseDTOLight>(curPage, list, totalCnt, 12);

		return bean;
	}
	
}
