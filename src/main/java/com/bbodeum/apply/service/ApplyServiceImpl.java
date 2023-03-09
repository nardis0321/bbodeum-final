package com.bbodeum.apply.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.entity.Apply;
import com.bbodeum.apply.entity.ApplyId;
import com.bbodeum.apply.entity.ApplyStatus;
import com.bbodeum.apply.repository.ApplyRepository;
import com.bbodeum.course.dto.CourseDTO;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.dog.entity.Dog;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

@Service
public class ApplyServiceImpl implements ApplyService {
	@Autowired
	private ApplyRepository ar;
	private static final int CNT_PER_PAGE = 10;
	
	@Override
	public ApplyDTO getById(Long dogId, Long courseId) throws FindException {
		ApplyId id = new ApplyId(dogId, courseId);
		Optional<Apply> optA = ar.findById(id);
		if(optA.isPresent()) {
			Apply entity = optA.get();
			ApplyDTO dto = entity.toDTO(entity);
			return dto;
		} else {
			throw new FindException("신청을 찾을 수 없습니다");
		}
	}

	@Override
	public PageBean<ApplyDTO> getByDog(Long dogId, int curPage) throws FindException {
		List<ApplyDTO> list = new ArrayList<>();
		List<Apply> entityList = ar.findByDog(Dog.builder().dogId(dogId).build());
		entityList.forEach((e)->{
			ApplyDTO dto = ApplyDTO.builder()
					.course(e.getCourse().toDTONoApplies(e.getCourse()))
					.applyStatus(e.getApplyStatus())
					.createdDate(e.getCreatedDate())
					.modifiedDate(e.getModifiedDate())
					.build();
			list.add(dto);
		});
		int totalCnt = ar.totalCntOfDog(dogId);
		PageBean<ApplyDTO> bean = new PageBean<ApplyDTO>(curPage, list, totalCnt, CNT_PER_PAGE);
		return bean;
	}

	@Override
	public PageBean<ApplyDTO> getByCourse(Long courseId, int curPage) throws FindException {
		List<ApplyDTO> list = new ArrayList<>();
		List<Apply> entityList = ar.findByCourse(Course.builder().courseId(courseId).build());
		entityList.forEach((e)->{
			ApplyDTO dto = ApplyDTO.builder()
					.dog(e.getDog().toDTO(e.getDog()))
					.applyStatus(e.getApplyStatus())
					.createdDate(e.getCreatedDate())
					.modifiedDate(e.getModifiedDate())
					.build();
			list.add(dto);
		});
		int totalCnt = ar.totalCntOfDog(courseId);
		PageBean<ApplyDTO> bean = new PageBean<ApplyDTO>(curPage, list, totalCnt, CNT_PER_PAGE);
		return bean;
	}

	@Override
	public void addApply(Long dogId, Long courseId, String impUid, String merchantUid) throws AddException {
//		ApplyDTO dto = ApplyDTO.builder()
//				.dog(DogDTO.builder().dogId(dogId).build())
//				.course(CourseDTO.builder().courseId(courseId).build())
//				.applyStatus(ApplyStatus.APPLIED)
//				.build();
		Apply entity = Apply.builder()
				.dog(Dog.builder().dogId(dogId).build())
				.course(Course.builder().courseId(courseId).build())
				.applyStatus(ApplyStatus.APPLIED)
				.impUid(impUid)
				.merchantUid(merchantUid)
				.build();
		ar.save(entity);
	}

	@Override
	public void dropApply(Long dogId, Long courseId) throws ModifyException {
		ApplyDTO dto = ApplyDTO.builder()
				.dog(DogDTO.builder().dogId(dogId).build())
				.course(CourseDTO.builder().courseId(courseId).build())
				.applyStatus(ApplyStatus.DROPPED)
				.build();
		Apply entity = dto.toEntity(dto);
		ar.save(entity);
	}

}
