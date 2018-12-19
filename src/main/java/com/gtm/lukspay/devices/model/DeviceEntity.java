package com.gtm.lukspay.devices.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "lp_device")
public class DeviceEntity {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    @javax.persistence.Id
    private  Long id;

    private  String clientId;

    private  String deviceId;

    private  String name;

    private  String description;

    private  String comment;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<IpAddressEntity> addresses;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;

}
