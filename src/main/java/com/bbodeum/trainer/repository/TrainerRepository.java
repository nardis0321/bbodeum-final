package com.bbodeum.trainer.repository;

import org.springframework.data.repository.CrudRepository;

import com.bbodeum.trainer.entity.Trainer;

public interface TrainerRepository extends CrudRepository<Trainer, String> {
	public Trainer findFirstByOrderByTrIdDesc();
}
