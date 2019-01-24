package com.yci.aesculapus.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

@Transactional
@RestController
@Slf4j
@RequestMapping(path = "/patients")
public class PatientController extends AbstractController {




}
