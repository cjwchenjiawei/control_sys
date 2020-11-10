package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Confer;
import com.lovo.model.Thing;

import java.util.Map;

public interface IConferService {
    boolean addConfer(Map<String, Object> map);

    PageInfo<Confer> listByPage(int pageNo, int pageSize, Integer thingId);
}
