package com.bbodeum.trainer.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbodeum.trainer.service.TrainerService;

@RestController
@RequestMapping("trainer/*")
public class TrainerController {
	@Autowired
	private TrainerService service;

}
