package com.bbodeum.apply.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbodeum.apply.dto.ApplyDTO;
import com.bbodeum.apply.entity.Apply;
import com.bbodeum.apply.entity.ApplyId;
import com.bbodeum.apply.entity.ApplyStatus;
import com.bbodeum.apply.repository.ApplyRepository;
import com.bbodeum.course.entity.Course;
import com.bbodeum.dog.entity.Dog;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

@Service
public class ApplyServiceImpl implements ApplyService {
	@Autowired
	private ApplyRepository ar;
	
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
	public List<ApplyDTO> getByDog(Dog d) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApplyDTO> getByCourse(Course c) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addApply(ApplyDTO dto) throws AddException {
		dto.setApplyStatus(ApplyStatus.APPLIED);
		Apply entity = dto.toEntity(dto);
		ar.save(entity);
	}

	@Override
	public void updateApply(ApplyDTO dto) throws ModifyException {

		Optional<Apply> optA = ar.findById(new ApplyId(dto.getDog().getDogId(), dto.getCourse().getCourseId()));
		if(optA.isPresent()) {
			Apply entity = dto.toEntity(dto);
			ar.save(entity);
		} else {
			throw new ModifyException("신청 수정에 실패했습니다");
		}
	}

}
