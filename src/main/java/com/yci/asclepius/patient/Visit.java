package com.yci.asclepius.patient;


import com.yci.asclepius.customer.Insurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "i_visit")
public class Visit {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insuranceVendor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    private BigDecimal persentDiscount;

    private BigDecimal amountDiscount;

    @OneToMany(mappedBy = "patientVisit", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<VisitRow> entries;


}
