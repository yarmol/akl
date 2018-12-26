package com.yci.aesculapus.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@RestController
@Slf4j
@RequestMapping(path = "/patients")
public class PatientController {

    @PostMapping
    public void addNew() {

    }

    @GetMapping
    public void getDevice() {

    }

    @GetMapping(path = "/all")
    public void getAllDevice() {

    }

    @PutMapping(path = "")
    public void changeDevice() {

    }
}
