package com.bbodeum.trainer.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@DynamicUpdate
public class Trainer {
	@Id
	private String trId;
	private String trPwd;
	private String trName;
	
	private String trSummary;
	private String trEducation;
	private String trExperience;
	private String trCertificates;
	
	@Enumerated(EnumType.STRING)
	private TrainerStatus trainerStatus;

	@Builder
	public Trainer(String trId, String trPwd, String trName, 
			String trSummary, String trEducation,
			String trExperience, String trCertificates, TrainerStatus trainerStatus) {
		this.trId = trId;
		this.trPwd = trPwd;
		this.trName = trName;
		this.trSummary = trSummary;
		this.trEducation = trEducation;
		this.trExperience = trExperience;
		this.trCertificates = trCertificates;
		this.trainerStatus = trainerStatus;
	}


	
}
