package com.gtm.lukspay.devices.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "lp_device_address")
public class IpAddressEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    String address;

    String description;

    String comment;

    String state;

    @Column(name = "date_change")
    private ZonedDateTime dateChange;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;
}
