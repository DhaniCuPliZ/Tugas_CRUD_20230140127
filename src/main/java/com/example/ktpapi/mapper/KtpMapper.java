package com.example.ktpapi.mapper;

import com.example.ktpapi.dto.KtpDto;
import com.example.ktpapi.entity.Ktp;

public class KtpMapper {

    public static KtpDto toDto(Ktp entity){

        KtpDto dto = new KtpDto();

        dto.setId(entity.getId());
        dto.setNomorKtp(entity.getNomorKtp());
        dto.setNamaLengkap(entity.getNamaLengkap());
        dto.setAlamat(entity.getAlamat());
        dto.setTanggalLahir(entity.getTanggalLahir());
        dto.setJenisKelamin(entity.getJenisKelamin());

        return dto;
    }

    public static Ktp toEntity(KtpDto dto){

        Ktp entity = new Ktp();

        entity.setId(dto.getId());
        entity.setNomorKtp(dto.getNomorKtp());
        entity.setNamaLengkap(dto.getNamaLengkap());
        entity.setAlamat(dto.getAlamat());
        entity.setTanggalLahir(dto.getTanggalLahir());
        entity.setJenisKelamin(dto.getJenisKelamin());

        return entity;
    }
}
