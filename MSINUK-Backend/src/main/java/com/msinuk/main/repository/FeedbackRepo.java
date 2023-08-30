package com.msinuk.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msinuk.main.model.Feedback;


@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long>{

}
