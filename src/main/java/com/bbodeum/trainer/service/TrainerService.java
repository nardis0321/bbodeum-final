package com.bbodeum.trainer.service;

import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;
import com.bbodeum.trainer.dto.TrainerDTO;

public interface TrainerService {
	public TrainerDTO getTrainerInfo(String id) throws FindException;
	public TrainerDTO signIn(String id, String pwd) throws FindException;
	public void signUp(TrainerDTO dto) throws AddException;
    public void updateTrainerInfo(TrainerDTO dto) throws ModifyException;
	public String getNextId() throws FindException;
}
