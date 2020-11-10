package com.lovo.model;

import lombok.Data;

@Data
public class Worm {
    private Integer wormId;

    private String wormName;

    private String wormHost;

    private String wormBreed;

    private String wormEnemy;

    private String wormLarvae;

    private String wormAdult;

    private String wormControl;

    private String wormDetriment;

    public Worm(String wormName, String wormHost, String wormBreed, String wormEnemy, String wormLarvae, String wormAdult, String wormControl, String wormDetriment) {
        this.wormName = wormName;
        this.wormHost = wormHost;
        this.wormBreed = wormBreed;
        this.wormEnemy = wormEnemy;
        this.wormLarvae = wormLarvae;
        this.wormAdult = wormAdult;
        this.wormControl = wormControl;
        this.wormDetriment = wormDetriment;
    }

    public Worm() {
    }
}