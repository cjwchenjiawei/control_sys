package com.lovo.model;

import lombok.Data;

import java.util.List;

@Data
public class Confer {
    private Integer conferId;

    private Thing thing;

    private String conferProficients;

    private String conferResult;

    private String conferTime;

    public Confer() {
    }

    public Confer(Thing thing, String conferProficients, String conferResult,String conferTime) {
        this.thing = thing;
        this.conferProficients = conferProficients;
        this.conferResult = conferResult;
        this.conferTime = conferTime;
    }
}