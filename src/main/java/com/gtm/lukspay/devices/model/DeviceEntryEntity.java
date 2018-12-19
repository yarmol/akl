package com.gtm.lukspay.devices.model;

import com.gtm.lukspay.devices.dto.Device;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = TABLE_PER_CLASS)
@Entity(name = "lp_device_entry")
public class DeviceEntryEntity extends EntryEntity<DeviceEntity> {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Id
    private  Long id;

}
