package com.esg.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NaturalIdRepository<Entity, ID> extends JpaRepository<Entity, ID> {
    Optional<Entity> naturalId(ID naturalId);
}

