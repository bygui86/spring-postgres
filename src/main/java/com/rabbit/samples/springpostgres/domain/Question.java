package com.rabbit.samples.springpostgres.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Mar 2019
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "questions")
public class Question extends Auditable {

	/*
		I’m using a @SequenceGenerator to generate the question’s id. You could also use PostgreSQL SERIAL column by
		specifying @GeneratedValue(strategy=GenerationType.IDENTITY) annotation. But a SequenceGenerator performs better in this case.
	 */
	@Id
	@GeneratedValue(generator = "question_generator")
	@SequenceGenerator(
			name = "question_generator",
			sequenceName = "question_sequence",
			initialValue = 1000
	)
	Long id;

	@NotBlank
	@Size(min = 3, max = 100)
	String title;

	@Column(columnDefinition = "text")
	String description;

}
