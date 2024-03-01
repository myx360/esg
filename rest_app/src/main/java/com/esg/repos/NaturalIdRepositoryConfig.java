package com.esg.repos;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Another boilerplate class, this one is just to enable the custom repository implementation
@Configuration
@EnableJpaRepositories(repositoryBaseClass = NaturalIdRepositoryImpl.class)
public class NaturalIdRepositoryConfig {
}
