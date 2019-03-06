package com.rabbit.samples.springpostgres.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Mar 2019
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {

	// no-op
}
