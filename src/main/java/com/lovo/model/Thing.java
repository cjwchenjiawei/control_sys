package com.lovo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Thing {
    private Integer thingId; //事件id

    private String thingName; //事件名称

    private String thingImages; //事件图片

    private String thingTime;  //事件发生时间

    private String thingType;  //事件类型

    private String thingStage;  //灾情状态

    private String thingDescription;  //事件描述

    private String thingDiscovery;  //事件发现途径

    private Area area;  //事件发生位置

    private String thingLoss;  //事件初步损失

    private String thingArea;  //事件受影响面积

    private String thingControl;  //事件防治方案

    public Thing() {
    }

    public Thing(String thingName, String thingImages, String thingTime, String thingType, String thingStage, String thingDescription, String thingDiscovery, Area area, String thingLoss, String thingArea, String thingControl) {
        this.thingName = thingName;
        this.thingImages = thingImages;
        this.thingTime = thingTime;
        this.thingType = thingType;
        this.thingStage = thingStage;
        this.thingDescription = thingDescription;
        this.thingDiscovery = thingDiscovery;
        this.area = area;
        this.thingLoss = thingLoss;
        this.thingArea = thingArea;
        this.thingControl = thingControl;
    }
}