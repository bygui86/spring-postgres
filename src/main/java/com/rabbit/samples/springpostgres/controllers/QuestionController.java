package com.rabbit.samples.springpostgres.controllers;


import com.rabbit.samples.springpostgres.domain.Question;
import com.rabbit.samples.springpostgres.exceptions.ResourceNotFoundException;
import com.rabbit.samples.springpostgres.repos.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Mar 2019
 */
@RestController
public class QuestionController {

	@Autowired
	private QuestionRepo questionRepo;

	@GetMapping("/questions")
	public Page<Question> getQuestions(Pageable pageable) {

		return questionRepo.findAll(pageable);
	}

	@PostMapping("/questions")
	public Question createQuestion(@Valid @RequestBody Question question) {

		return questionRepo.save(question);
	}

	@PutMapping("/questions/{questionId}")
	public Question updateQuestion(@PathVariable Long questionId,
	                               @Valid @RequestBody Question questionRequest) {

		return questionRepo.findById(questionId)
				.map(question -> {
					question.setTitle(questionRequest.getTitle());
					question.setDescription(questionRequest.getDescription());
					return questionRepo.save(question);
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}

	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {

		return questionRepo.findById(questionId)
				.map(question -> {
					questionRepo.delete(question);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}

}