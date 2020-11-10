package com.lovo.vo;

import lombok.Data;

@Data
public class AreaVO {
    private Integer areaId; //区域编号
    private String areaName; //区域名称
    private String areaForest;  //林中
    private String areaLand;   //地类
    private String areaTree;   //优势树种
    private String myClassName;    //负责小班


}
