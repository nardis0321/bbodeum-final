package com.bbodeum.dog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
public class Dog {
	@Id
	@GeneratedValue
	private Long dogId;
	
	@ManyToOne
	@JoinColumn(name = "dog_mem_id")
	private Member member;

	private String dogName;
	private Double dogWeight;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date dogBday;

	private String dogBreed;

	@Enumerated(EnumType.STRING)
	private DogStatus dogStatus;

	@Builder
	public Dog(Long dogId, Member member, String dogName, Double dogWeight, Date dogBday, String dogBreed,
			DogStatus dogStatus) {
		this.dogId = dogId;
		this.member = member;
		this.dogName = dogName;
		this.dogWeight = dogWeight;
		this.dogBday = dogBday;
		this.dogBreed = dogBreed;
		this.dogStatus = dogStatus;
	}
	
	public DogDTO toDTO(Dog d) {
		DogDTO dto = DogDTO.builder()
				.dogId(d.getDogId())
				.member(d.getMember().getMemEmail())
				.dogName(d.getDogName())
				.dogWeight(d.getDogWeight())
				.dogBday(d.getDogBday())
				.dogBreed(d.getDogBreed())
				.dogStatus(d.getDogStatus())
				.build();
		return dto;
	}
}