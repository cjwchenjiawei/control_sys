package com.lovo.model;

import lombok.Data;

@Data
public class Drug {
    private Integer drugId;

    private String drugName;

    private String drugControl;

    private String drugType;

    private String drugUsage;

    public Drug() {
    }

    public Drug(String drugName, String drugControl, String drugType, String drugUsage) {
        this.drugName = drugName;
        this.drugControl = drugControl;
        this.drugType = drugType;
        this.drugUsage = drugUsage;
    }

    public Drug(String drugName, String drugControl, String drugType) {
        this.drugName = drugName;
        this.drugControl = drugControl;
        this.drugType = drugType;
    }
}