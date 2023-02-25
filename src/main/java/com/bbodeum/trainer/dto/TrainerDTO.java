package com.bbodeum.trainer.dto;

import com.bbodeum.trainer.entity.Trainer;
import com.bbodeum.trainer.entity.TrainerStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainerDTO {
	private String trId;
	private String trPwd;
	private String trName;
	
	private String trSummary;
	private String trEducation;
	private String trExperience;
	private String trCertificates;
	
	private TrainerStatus trStatus;
	
	public Trainer toEntity(TrainerDTO dto) {
		Trainer entity = Trainer.builder()
				.trId(dto.getTrId())
				.trPwd(dto.getTrPwd())
				.trName(dto.getTrName())
				.trainerStatus(dto.getTrStatus())
				.trSummary(dto.getTrSummary())
				.trExperience(dto.getTrExperience())
				.trEducation(dto.getTrEducation())
				.trCertificates(dto.getTrCertificates())
				.build();
		return entity;
	}
}
