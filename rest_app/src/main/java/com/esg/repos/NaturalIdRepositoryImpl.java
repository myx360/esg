package com.esg.repos;

import java.io.Serializable;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;


// This class is mostly just copied boilerplate, it's a custom implementation
// of the JpaRepository interface that allows us to use the naturalId method
public class NaturalIdRepositoryImpl<E, ID extends Serializable, NID extends Serializable> extends SimpleJpaRepository<E, ID> implements NaturalIdRepository<E, ID> {
    private final EntityManager entityManager;

    public NaturalIdRepositoryImpl(JpaEntityInformation<E, ?> entityInfo, EntityManager entityManager) {
        super(entityInfo, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<E> naturalId(ID naturalId) {
        return entityManager.unwrap(Session.class)
	                        .bySimpleNaturalId(this.getDomainClass())
                            .loadOptional(naturalId);
    }

}
