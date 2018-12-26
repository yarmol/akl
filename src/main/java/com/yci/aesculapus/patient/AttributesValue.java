package com.yci.aesculapus.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "i_patient_attribute_value")
public class AttributesValue {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_type_id")
    private Attributes type;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private Serializable value;

}
