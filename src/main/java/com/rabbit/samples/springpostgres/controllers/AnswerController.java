package com.rabbit.samples.springpostgres.controllers;


import com.rabbit.samples.springpostgres.domain.Answer;
import com.rabbit.samples.springpostgres.exceptions.ResourceNotFoundException;
import com.rabbit.samples.springpostgres.repos.AnswerRepo;
import com.rabbit.samples.springpostgres.repos.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Mar 2019
 */
@RestController
public class AnswerController {

	@Autowired
	private AnswerRepo answerRepo;

	@Autowired
	private QuestionRepo questionRepo;

	@GetMapping("/questions/{questionId}/answers")
	public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {

		return answerRepo.findByQuestionId(questionId);
	}

	@PostMapping("/questions/{questionId}/answers")
	public Answer addAnswer(@PathVariable Long questionId,
	                        @Valid @RequestBody Answer answer) {

		return questionRepo.findById(questionId)
				.map(question -> {
					answer.setQuestion(question);
					return answerRepo.save(answer);
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}

	@PutMapping("/questions/{questionId}/answers/{answerId}")
	public Answer updateAnswer(@PathVariable Long questionId,
	                           @PathVariable Long answerId,
	                           @Valid @RequestBody Answer answerRequest) {

		if(!questionRepo.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id " + questionId);
		}

		return answerRepo.findById(answerId)
				.map(answer -> {
					answer.setText(answerRequest.getText());
					return answerRepo.save(answer);
				}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
	}

	@DeleteMapping("/questions/{questionId}/answers/{answerId}")
	public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,
	                                      @PathVariable Long answerId) {

		if(!questionRepo.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id " + questionId);
		}

		return answerRepo.findById(answerId)
				.map(answer -> {
					answerRepo.delete(answer);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
	}

}
