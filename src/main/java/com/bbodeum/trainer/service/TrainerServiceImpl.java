
package com.bbodeum.trainer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.trainer.dto.TrainerDTO;
import com.bbodeum.trainer.entity.Trainer;
import com.bbodeum.trainer.entity.TrainerStatus;
import com.bbodeum.trainer.repository.TrainerRepository;
@Service
public class TrainerServiceImpl implements TrainerService {
	@Autowired
	private TrainerRepository tr;
	
	
	
	@Override
	public TrainerDTO getTrainerInfo(String id) throws FindException {
		Optional<Trainer> optT = tr.findById(id);
		if(optT.isPresent()) {
			Trainer entity = optT.get();
			TrainerDTO dto = entity.toDTOWithoutPwd(entity);
			return dto;
		} else {
			throw new FindException("정보를 찾을 수 없습니다");
		}
	}

	@Override
	public TrainerDTO signIn(String id, String pwd) throws FindException {
		Optional<Trainer> optT = tr.findById(id);
		if(optT.isPresent()) {
			Trainer entity = optT.get();
			if(entity.getTrPwd().equals(pwd)) {
				TrainerDTO dto = entity.toDTOWithoutPwd(entity);
				return dto;
			} else {
				throw new FindException("로그인 실패");			
			}
		}else {
			throw new FindException("로그인 실패");			
		}
	}
	
	@Override
	public String getNextId() throws FindException {
		Trainer entity = tr.findFirstByOrderByTrIdDesc();
		String lastTrNum = entity.getTrId().substring(2);
		int nextTrNum = Integer.parseInt(lastTrNum)+1;
		int length = (int)Math.log10(nextTrNum)+1;
		String nextId = "TR";
		for(int i=1; i<=(4-length); i++) {
			nextId += "0";
		}
		return nextId+nextTrNum;
	}

	@Override
	public void signUp(TrainerDTO dto) throws AddException {
		try {
			dto.setTrStatus(TrainerStatus.TRAINER);
			Trainer entity = dto.toEntity(dto);
			tr.save(entity);	
		} catch(Exception e) {
			throw new AddException("가입 실패");
		}
	}

	@Override
	public void updateTrainerInfo(TrainerDTO dto) throws ModifyException {
		String id = dto.getTrId();
		Optional<Trainer> optT = tr.findById(id);
		if(optT.isPresent()) {
			String pwd = dto.getTrPwd();
			String name = dto.getTrName();
			TrainerStatus trSt = dto.getTrStatus();
			String trSummary = dto.getTrSummary();
			String edu = dto.getTrEducation();
			String exp = dto.getTrExperience();
			String cert = dto.getTrCertificates();
			
			//dynamic update 위해 null값 채우기
			Trainer entity = optT.get(); 
			pwd = (pwd == null) ? entity.getTrPwd() : pwd;
			name = (name == null) ? entity.getTrName() : name;
			trSt = (trSt == null) ? entity.getTrainerStatus() : trSt;
			trSummary = (trSummary==null) ? entity.getTrSummary() : trSummary;
			edu = (edu==null) ? entity.getTrEducation() : edu;
			exp = (exp==null) ? entity.getTrExperience() : exp;
			cert = (cert==null) ? entity.getTrCertificates() : cert;
			entity = null;
			
			entity = Trainer.builder()
					.trId(id)
					.trPwd(pwd)
					.trName(name)
					.trainerStatus(trSt)
					.trSummary(trSummary)
					.trEducation(edu)
					.trExperience(exp)
					.trCertificates(cert)
					.build();
			tr.save(entity);
		}
	}
}
