package com.gtm.lukspay.devices.dto;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

    private Long id;

    private String clientId;

    private String deviceId;

    private String comment;

    private String name;

    private String description;

    private String state;

    private List<IpAddress> ipAddress;


}

