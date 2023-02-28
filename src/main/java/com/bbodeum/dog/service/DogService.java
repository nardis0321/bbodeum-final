package com.bbodeum.dog.service;

import java.util.List;

import com.bbodeum.dog.dto.DogDTO;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;
import com.bbodeum.exception.ModifyException;

public interface DogService {
	public DogDTO getDog(Long dogId) throws FindException;
	public List<DogDTO> getDogsByMem(String id) throws FindException;
	public void addDog(DogDTO dto) throws AddException;
	public void updateDog(DogDTO dto) throws ModifyException;
	public void deleteDog(Long dogId) throws ModifyException;
}
