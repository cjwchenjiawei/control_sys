package com.lovo.model;

import lombok.Data;

@Data
public class Mouse {
    private Integer mouseId;

    private String mouseName;

    private String mouseFood;

    private String mouseBreed;

    private String mouseEnemy;

    private String mouseImages;

    private String mouseControl;

    private String mouseDetriment;

    public Mouse() {
    }

    public Mouse(String mouseName, String mouseFood, String mouseBreed, String mouseEnemy, String mouseImages, String mouseControl, String mouseDetriment) {
        this.mouseName = mouseName;
        this.mouseFood = mouseFood;
        this.mouseBreed = mouseBreed;
        this.mouseEnemy = mouseEnemy;
        this.mouseImages = mouseImages;
        this.mouseControl = mouseControl;
        this.mouseDetriment = mouseDetriment;
    }
}