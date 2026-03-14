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
    @PostMapping
    public KtpDto create(@RequestBody KtpDto dto){
        return service.create(dto);
    }

    @GetMapping
    public List<KtpDto> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public KtpDto getById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public KtpDto update(@PathVariable Integer id, @RequestBody KtpDto dto){
        return service.update(id,dto);
    }

}