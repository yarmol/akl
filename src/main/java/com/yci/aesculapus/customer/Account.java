package com.yci.aesculapus.customer;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity(name = "i_cashdesk")
public class Account {


    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private  Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String code;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;

}
