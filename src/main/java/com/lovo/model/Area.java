package com.lovo.model;

import lombok.Data;

@Data
public class Area {
    private Integer areaId;

    private String areaName;

    private String areaForest;

    private String areaTree;

    private String areaLand;

    private MyClass myClass;

    public Area() {
    }

    public Area(String areaName, String areaForest, String areaTree, String areaLand) {
        this.areaName = areaName;
        this.areaForest = areaForest;
        this.areaTree = areaTree;
        this.areaLand = areaLand;
    }
}