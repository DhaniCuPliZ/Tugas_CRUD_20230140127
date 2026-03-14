package com.example.ktpapi.controller;

import com.example.ktpapi.dto.KtpDto;
import com.example.ktpapi.service.KtpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin
public class KtpController {

    private final KtpService service;

    public KtpController(KtpService service) {
        this.service = service;
    }


}