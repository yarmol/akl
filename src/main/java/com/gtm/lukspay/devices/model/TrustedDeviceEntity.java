package com.gtm.lukspay.devices.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "lp_trusted_device")
public class TrustedDeviceEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DeviceEntity.class)
    private DeviceEntity device;

    private String state;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;
}

