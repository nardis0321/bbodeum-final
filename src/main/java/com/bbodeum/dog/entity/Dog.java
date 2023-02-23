package com.bbodeum.dog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.bbodeum.member.entity.Member;
import com.bbodeum.member.entity.MemberStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@ToString

@Entity
@Table(name = "dog")
@DynamicUpdate
public class Dog {
	@Id
	@GeneratedValue
	private Long dogId;
	
	@ManyToOne
	@JoinColumn(name = "dog_mem_id")
	private Member member;

	private String dogName;
	
	private float dogWeight;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date dogBday;

	private String dogBreed;

	@Enumerated(EnumType.STRING)
	private DogStatus dogStatus;

}
