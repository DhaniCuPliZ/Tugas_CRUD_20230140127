package com.example.ktpapi.controller;

import com.example.ktpapi.dto.KtpDto;
import com.example.ktpapi.model.ApiResponse;
import com.example.ktpapi.service.KtpService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
public class KtpController {

    private final KtpService service;

    public KtpController(KtpService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<KtpDto> create(@RequestBody KtpDto dto) {

        KtpDto result = service.create(dto);

        return new ApiResponse<>(
                true,
                "Data KTP berhasil ditambahkan",
                result
        );
    }

    @GetMapping
    public ApiResponse<List<KtpDto>> getAll() {

        List<KtpDto> data = service.getAll();

        return new ApiResponse<>(
                true,
                "Data KTP berhasil diambil",
                data
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<KtpDto> getById(@PathVariable Integer id) {

        KtpDto data = service.getById(id);

        return new ApiResponse<>(
                true,
                "Data KTP ditemukan",
                data
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<KtpDto> update(@PathVariable Integer id, @RequestBody KtpDto dto) {

        KtpDto data = service.update(id, dto);

        return new ApiResponse<>(
                true,
                "Data KTP berhasil diupdate",
                data
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {

        service.delete(id);

        return new ApiResponse<>(
                true,
                "Data KTP berhasil dihapus",
                null
        );
    }
}
