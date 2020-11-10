package com.lovo.model;

import lombok.Data;

@Data
public class MyClass {
    private Integer classId;

    private String className;

    private String classLeader;

    private String classTel;

    private Integer classNumber;

    private Area area;

    private String classDate;

    public MyClass() {
    }

    public MyClass(String className, String classLeader, String classTel, Integer classNumber, Area area, String classDate) {
        this.className = className;
        this.classLeader = classLeader;
        this.classTel = classTel;
        this.classNumber = classNumber;
        this.area = area;
        this.classDate = classDate;
    }
}