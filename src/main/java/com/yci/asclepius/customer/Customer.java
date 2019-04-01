package com.yci.asclepius.customer;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity(name = "i_customer")
public abstract class Customer {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private  Long id;

    private String code;

    private String name;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;


}
