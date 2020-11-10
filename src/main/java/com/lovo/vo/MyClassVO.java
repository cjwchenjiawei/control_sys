package com.lovo.vo;

import lombok.Data;

@Data
public class MyClassVO {
    private int classId; //小班编号
    private String className; //小班名称
    private String classLeader; //负责人
    private String classTel; //负责人电话
    private int classNumber; //小班人数
    private String areaName; //负责区域
    private String areaForest; //林种
    private String areaLand; //地类
    private String areaTree; //优势树种
    private String classDate; //创建日期

}
