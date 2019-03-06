package com.rabbit.samples.springpostgres.repos;

import com.rabbit.samples.springpostgres.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Mar 2019
 */
@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

	// no-op
}
