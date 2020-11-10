package com.lovo.model;

import lombok.Data;

import java.util.List;

@Data
public class OutwareHouse {
    private Integer outwarehouseId;

    private String outwarehouseUser;

    private MyClass myClass;

    private List<OutwareHouseDrug> drugs;

    private String outwarehouseTime;

    public OutwareHouse() {
    }

    public OutwareHouse(String outwarehouseUser, MyClass myClass, List<OutwareHouseDrug> drugs, String outwarehouseTime) {
        this.outwarehouseUser = outwarehouseUser;
        this.myClass = myClass;
        this.drugs = drugs;
        this.outwarehouseTime = outwarehouseTime;
    }
}