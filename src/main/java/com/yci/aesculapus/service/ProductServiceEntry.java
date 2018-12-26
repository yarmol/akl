package com.yci.aesculapus.service;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "i_service")
public class ProductServiceEntry {


    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private  Long id;

    private Integer code;

    private String name;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private ProductServiceTypeEntity type;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;

}
