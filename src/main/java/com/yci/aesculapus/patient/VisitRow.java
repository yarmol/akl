package com.yci.aesculapus.patient;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "i_visit_entry")
public class VisitRow {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_visit_id")
    private Visit patientVisit;

    @OneToOne(fetch = FetchType.EAGER,targetEntity = RowDescriptor.class)
    private RowDescriptor visitDescriptor;

    private BigDecimal price;

    private BigDecimal amount;



}
