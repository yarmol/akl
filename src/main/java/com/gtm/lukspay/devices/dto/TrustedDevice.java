package com.gtm.lukspay.devices.dto;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustedDevice {

    private Long id;

    private Device device;

    private String state;


}

