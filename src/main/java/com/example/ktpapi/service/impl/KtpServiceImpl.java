package com.example.ktpapi.service.impl;

import com.example.ktpapi.dto.KtpDto;
import com.example.ktpapi.entity.Ktp;
import com.example.ktpapi.mapper.KtpMapper;
import com.example.ktpapi.repository.KtpRepository;
import com.example.ktpapi.service.KtpService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    private final KtpRepository repository;

    public KtpServiceImpl(KtpRepository repository) {
        this.repository = repository;
    }

    @Override
    public KtpDto create(KtpDto dto) {

        repository.findByNomorKtp(dto.getNomorKtp())
                .ifPresent(x -> {
                    throw new RuntimeException("Nomor KTP sudah ada");
                });

        Ktp entity = KtpMapper.toEntity(dto);

        entity = repository.save(entity);

        return KtpMapper.toDto(entity);
    }

    @Override
    public List<KtpDto> getAll() {

        return repository.findAll()
                .stream()
                .map(KtpMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getById(Integer id) {

        Ktp entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        return KtpMapper.toDto(entity);
    }

    @Override
    public KtpDto update(Integer id, KtpDto dto) {

        Ktp entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        entity.setNomorKtp(dto.getNomorKtp());
        entity.setNamaLengkap(dto.getNamaLengkap());
        entity.setAlamat(dto.getAlamat());
        entity.setTanggalLahir(dto.getTanggalLahir());
        entity.setJenisKelamin(dto.getJenisKelamin());

        entity = repository.save(entity);

        return KtpMapper.toDto(entity);
    }

    @Override
    public void delete(Integer id) {

        Ktp entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        repository.delete(entity);
    }
}

