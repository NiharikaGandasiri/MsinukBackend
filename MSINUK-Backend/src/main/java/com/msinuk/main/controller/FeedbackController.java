package com.msinuk.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.msinuk.main.model.FeedbackResponse;
import com.msinuk.main.service.FeedbackService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/")


public class FeedbackController {
	
	@Autowired
	private FeedbackService service;
	
	@GetMapping("/addFeedback")
	public FeedbackResponse addFeedback(@RequestParam("feedback") String feedback) throws JsonMappingException, JsonProcessingException{
		return this.service.addFeedback(feedback);
	}

}
