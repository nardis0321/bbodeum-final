package com.bbodeum.dog.dto;

import java.util.Date;

import com.bbodeum.dog.entity.Dog;
import com.bbodeum.dog.entity.DogStatus;
import com.bbodeum.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DogDTO {
	private Long dogId;
	private MemberDTO member;
	private String dogName;
	private Double dogWeight;
	private Date dogBday;
	private String dogBreed;
	private DogStatus dogStatus;
	
	public Dog toEntity(DogDTO d) {
		Dog dog = Dog.builder()
				.dogId(d.getDogId())
				.member(d.getMember().toEntity(member))
				.dogName(d.getDogName())
				.dogWeight(d.getDogWeight())
				.dogBday(d.getDogBday())
				.dogBreed(d.getDogBreed())
				.dogStatus(d.getDogStatus())
				.build();
		return dog;
	}
}
