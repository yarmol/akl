package com.yci.asclepius.patient;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gtm.lukspay.constant.DatePatternConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "i_patient")
public class Patient {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private Long id;

    private String code;

    private String firstName;

    private String lastName;

    private String patronymicName;

    private String comment;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AttributesValue> attributes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DatePatternConstant.DATE_PATTERN)
    private LocalDate birthday;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;
}
