package com.lovo.vo;

import com.lovo.model.Area;
import lombok.Data;

@Data
public class ThingVO {
    private Integer thingId; //事件id

    private String thingName; //事件名称

    private String thingImages; //事件图片

    private String thingTime;  //事件发生时间

    private String thingType;  //事件类型

    private String thingStage;  //灾情状态

    private String thingDescription;  //事件描述

    private String thingDiscovery;  //事件发现途径

    private String areaName;  //事件发生位置

    private String className; //负责小班

    private String thingLoss;  //事件初步损失

    private String thingArea;  //事件受影响面积

    private String thingControl;  //事件防治方案
}
