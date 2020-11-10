package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.MyClass;
import com.lovo.vo.MyClassVO;

import java.util.List;

public interface IMyClassService {
    PageInfo<MyClassVO> listByPage(int pageNo, int pageSize, String className, String areaName);

    boolean insertSelective(MyClass mc);

    boolean updateByPrimaryKeySelective(MyClass mc);

    MyClass queryClass(Integer myClassId);

    List<MyClass> queryClassList();
}
