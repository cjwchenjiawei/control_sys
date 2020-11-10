package com.lovo.model;

import lombok.Data;

@Data
public class Disease {
    private Integer diseaseId;

    private String diseaseName;

    private String diseaseSource;

    private String diseaseSymptom;

    private String diseaseLaw;

    private String diseaseImages;

    private String diseaseControl;

    private String diseaseDetriment;

    public Disease() {
    }

    public Disease(String diseaseName, String diseaseSource, String diseaseSymptom, String diseaseLaw, String diseaseImages, String diseaseControl, String diseaseDetriment) {
        this.diseaseName = diseaseName;
        this.diseaseSource = diseaseSource;
        this.diseaseSymptom = diseaseSymptom;
        this.diseaseLaw = diseaseLaw;
        this.diseaseImages = diseaseImages;
        this.diseaseControl = diseaseControl;
        this.diseaseDetriment = diseaseDetriment;
    }
}