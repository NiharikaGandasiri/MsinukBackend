package com.msinuk.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.msinuk.main.model.Feedback;
import com.msinuk.main.model.FeedbackResponse;
import com.msinuk.main.repository.FeedbackRepo;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepo repo;
	
	
	public FeedbackResponse addFeedback(String feedback) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		Feedback feedbackDTO = mapper.readValue(feedback, Feedback.class);
		this.repo.save(feedbackDTO);
		return new FeedbackResponse(true,"Thanks for your valuable Feedback");
	}
}
