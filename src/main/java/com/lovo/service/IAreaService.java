package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Area;
import com.lovo.vo.AreaVO;

import java.util.List;

public interface IAreaService {
    PageInfo<AreaVO> listByPage(int pageNo, int pageSize, String areaName, String areaForest, String myClassName);

    boolean insert(Area a);

    List<Area> queryAreaList();

    List<Area> queryAllAreaList();

    Area queryById(Integer myClassId);
}
