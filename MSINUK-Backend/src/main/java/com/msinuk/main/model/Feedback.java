package com.msinuk.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "feedback")

public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double rating ;
    private int recommendValue;
    private String feedback;
    private String username;
    
    public Feedback() {
    	
    }
    
	public Feedback(Long id, double rating, int recommendValue, String feedback, String username) {
		super();
		this.id = id;
		this.rating = rating;
		this.recommendValue = recommendValue;
		this.feedback = feedback;
		this.username = username;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getRecommendValue() {
		return recommendValue;
	}
	public void setRecommendValue(int recommendValue) {
		this.recommendValue = recommendValue;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
