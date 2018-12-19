package com.gtm.lukspay.devices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.gtm.lukspay.devices.dto.Device;
import com.gtm.lukspay.devices.model.DeviceEntity;
import com.gtm.lukspay.devices.model.DeviceEntryEntity;
import com.gtm.lukspay.devices.repository.DeviceEntityEntryRepository;
import com.gtm.lukspay.devices.repository.DeviceEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.tuple.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.ws.rs.POST;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Transactional
@RestController
@Slf4j
@RequestMapping(path = "/entries")
public class DeviceEntryController {


    @Autowired
    DeviceEntityEntryRepository repository;

    @Autowired
    DeviceEntityRepository deviceRepository;

  //  @Autowired


    @GetMapping
    public void getNew() {


        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();


        Table<Integer,String,String> table = HashBasedTable.create();

        table.put(1,"Device", "DeviceValue 1");
        table.put(2,"Device", "DeviceValue 2");
        table.put(2,"Count", "2");
        table.put(3,"Device", "DeviceValue 3");


        log.info(mapper.valueToTree(table).toString());


    }

    @PostMapping
    public void addNew(Device deviceDto, BigDecimal count, BigDecimal sum) {

        DeviceEntity deviceEntry;

          Optional<DeviceEntity> byId = deviceRepository.findById(deviceDto.getId());
        if (byId.isPresent()) {
            deviceEntry = byId.get();
        } else {
            deviceEntry = new DeviceEntity();
            deviceEntry.setDeviceId(deviceDto.getDeviceId());
            deviceEntry.setClientId(deviceDto.getClientId());
            deviceRepository.save(deviceEntry);
        }

        DeviceEntryEntity entry = new DeviceEntryEntity();
        entry.setNumber(UUID.randomUUID().toString());
        entry.setDateOperation(ZonedDateTime.now());

        entry.setObject(deviceEntry);
        entry.setAmount(count);
        entry.setSum(sum);

       // repository.save(entry);

    }

}



