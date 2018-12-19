package com.gtm.lukspay.devices.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
public abstract class EntryEntity<T> {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private Long id;

    @Column(name = "date_operation")
    private ZonedDateTime dateOperation;

    private String number;

    @Column(name = "object")
    private T object;

    private BigDecimal amount;

    private BigDecimal sum;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;

}
