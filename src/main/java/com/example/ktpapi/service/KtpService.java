package com.example.ktpapi.service;

import com.example.ktpapi.dto.KtpDto;
import java.util.List;

public interface KtpService {

    KtpDto create(KtpDto dto);

    List<KtpDto> findAll();

    KtpDto findById(Integer id);

    KtpDto update(Integer id, KtpDto dto);

    void delete(Integer id);
}