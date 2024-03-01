package com.esg.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import org.hibernate.annotations.NaturalId;

import com.esg.records.CustomerRecord;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Customers")
// @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE) // Add caching, type dependent on the use case
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Nonnull
    private String reference;

    private CustomerRecord customerRecord;
    
    public Customer(final String reference, final CustomerRecord customerRecord) {
        this.reference = reference;
        this.customerRecord = customerRecord;
    }
}
