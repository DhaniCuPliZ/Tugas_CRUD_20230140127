package com.example.ktpapi.service.impl;

import com.example.ktpapi.dto.KtpDto;
import com.example.ktpapi.entity.Ktp;
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

        if(repository.findByNomorKtp(dto.getNomorKtp()).isPresent()){
            throw new RuntimeException("Nomor KTP sudah ada");
        }

        Ktp ktp = new Ktp();
        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());

        repository.save(ktp);

        dto.setId(ktp.getId());
        return dto;
    }

    @Override
    public List<KtpDto> findAll() {
        return repository.findAll().stream().map(ktp -> {
            KtpDto dto = new KtpDto();
            dto.setId(ktp.getId());
            dto.setNomorKtp(ktp.getNomorKtp());
            dto.setNamaLengkap(ktp.getNamaLengkap());
            dto.setAlamat(ktp.getAlamat());
            dto.setTanggalLahir(ktp.getTanggalLahir());
            dto.setJenisKelamin(ktp.getJenisKelamin());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public KtpDto findById(Integer id) {
        Ktp ktp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        KtpDto dto = new KtpDto();
        dto.setId(ktp.getId());
        dto.setNomorKtp(ktp.getNomorKtp());
        dto.setNamaLengkap(ktp.getNamaLengkap());
        dto.setAlamat(ktp.getAlamat());
        dto.setTanggalLahir(ktp.getTanggalLahir());
        dto.setJenisKelamin(ktp.getJenisKelamin());

        return dto;
    }

    @Override
    public KtpDto update(Integer id, KtpDto dto) {

        Ktp ktp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());

        repository.save(ktp);

        dto.setId(ktp.getId());
        return dto;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}