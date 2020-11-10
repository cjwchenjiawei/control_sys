package com.lovo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Proficient {
    private Integer proficientId;

    private String proficientName;

    private String proficientGender;

    private String proficientBirthday;

    private String proficientImages;

    private String proficientSpeciality;

    private String proficientJob;

    private String proficientTel;

    private String proficientUnit;

    private String proficientAddress;

    private String proficientEmail;

    public Proficient() {
    }

    public Proficient(String proficientName, String proficientGender, String proficientBirthday, String proficientImages, String proficientSpeciality, String proficientJob, String proficientTel, String proficientUnit, String proficientAddress, String proficientEmail) {
        this.proficientName = proficientName;
        this.proficientGender = proficientGender;
        this.proficientBirthday = proficientBirthday;
        this.proficientImages = proficientImages;
        this.proficientSpeciality = proficientSpeciality;
        this.proficientJob = proficientJob;
        this.proficientTel = proficientTel;
        this.proficientUnit = proficientUnit;
        this.proficientAddress = proficientAddress;
        this.proficientEmail = proficientEmail;
    }

    public Proficient(Integer proficientId, String proficientJob, String proficientTel, String proficientUnit, String proficientEmail) {
        this.proficientId = proficientId;
        this.proficientJob = proficientJob;
        this.proficientTel = proficientTel;
        this.proficientUnit = proficientUnit;
        this.proficientEmail = proficientEmail;
    }
}