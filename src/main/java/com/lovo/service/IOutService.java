package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.vo.OutwareHouseVO;

public interface IOutService {
    PageInfo<OutwareHouseVO> listByPage(int pageNo, int pageSize, String myClassName, String startTime, String endTime);

    boolean addOut(Integer classId, String userName);
}
