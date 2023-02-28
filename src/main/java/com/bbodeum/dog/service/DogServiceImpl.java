package com.bbodeum.dog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.dog.entity.Dog;
import com.bbodeum.dog.entity.DogStatus;
import com.bbodeum.dog.repository.DogRepository;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.member.dto.MemberDTO;
import com.bbodeum.member.entity.Member;
@Service
public class DogServiceImpl implements DogService {
	@Autowired
	private DogRepository dR;
	
	@Override
	public DogDTO getDog(Long dogId) throws FindException {
		
		Optional<Dog> optD = dR.findById(dogId);
		if(optD.isPresent()) {
			Dog d = optD.get();
			DogDTO dto = d.toDTO(d);
			return dto;
		} else {
			throw new FindException("해당 반려견을 찾을 수 없습니다");
		}
	}

	@Override
	public List<DogDTO> getDogsByMem(String id) throws FindException{
		Member m = Member.builder().memEmail(id).build();
		List<Dog> list = dR.findByMember(m);
		List<DogDTO> dtoList = new ArrayList<DogDTO>();
		list.forEach((d)->{
			DogDTO dto = d.toDTO(d);
			dtoList.add(dto);
		});
		return dtoList;		
//			throw new FindException("해당 반려견을 찾을 수 없습니다");
	}
	
	@Override
	public void addDog(DogDTO dto) throws AddException{
		try {
			dto.setDogStatus(DogStatus.ACTIVE);
			Dog d = dto.toEntity(dto);
			dR.save(d);
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException("반려견 정보 추가에 실패했습니다");
		}
	}

	@Override
	public void updateDog(DogDTO dto) throws ModifyException {
			Long id = dto.getDogId();
			Optional<Dog> optD = dR.findById(id);
			if(optD.isPresent()) {
				String memId = dto.getMember();
				String name = dto.getDogName();
				Double weight = dto.getDogWeight();
				Date bday = dto.getDogBday();
				String breed = dto.getDogBreed();
				DogStatus dogSt = dto.getDogStatus();
	
				Dog d = optD.get();
				Member mem = (memId==null) ? d.getMember() : Member.builder().memEmail(memId).build();
				name = (name==null) ? d.getDogName() : name;
				weight = (weight==null) ? d.getDogWeight() : weight;
				bday = (bday==null) ? d.getDogBday() : bday;
				breed = (breed==null) ? d.getDogBreed() : breed;
				dogSt = (dogSt==null) ? d.getDogStatus() : dogSt;
				d = null;
				
				d = Dog.builder()
						.dogId(id)
						.member(mem)
						.dogName(name)
						.dogWeight(weight)
						.dogBday(bday)
						.dogBreed(breed)
						.dogStatus(dogSt)
						.build();
				dR.save(d);
			}
//			throw new ModifyException("반려견 정보 수정에 실패했습니다");
	}

	@Override
	public void deleteDog(Long dogId) throws ModifyException {
		//TODO check apply
		DogDTO dto = DogDTO.builder()
				.dogId(dogId)
				.dogStatus(DogStatus.DELETED)
				.build();
		updateDog(dto);
	}


}
