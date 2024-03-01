package com.esg.records;

import jakarta.persistence.Embeddable;

@Embeddable
public record CustomerRecord(
    String name,
    String address1,
    String address2,
    String town,
    String county,
    String postcode,
    String country
    ) {}

