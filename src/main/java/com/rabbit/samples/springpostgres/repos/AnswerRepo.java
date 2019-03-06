package com.rabbit.samples.springpostgres.repos;

import com.rabbit.samples.springpostgres.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Mar 2019
 */
@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {

	List<Answer> findByQuestionId(Long questionId);

}
