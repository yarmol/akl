package com.gtm.lukspay.devices.dto;

import lombok.*;

import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpAddress {

    Long id;

    String address;

    String description;

    String comment;

    String state;
}
