package com.yci.asclepius.employee;


import com.yci.asclepius.service.ProductServiceTypeEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "i_salary_persent")
public class SalaryPercent {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private  Long id;

    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @Enumerated(EnumType.STRING)
    private ProductServiceTypeEntity serviceType;

    private BigDecimal value;


}
