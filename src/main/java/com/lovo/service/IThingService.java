package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Thing;
import com.lovo.vo.ThingVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface IThingService {
    PageInfo<ThingVO> listByPage(int pageNo, int pageSize, String thingName, String thingStage, String areaName, String startTime, String endTime);

    boolean insert(Thing thing);

    boolean update(Thing thing);

    PageInfo<ThingVO> conferList(int pageNo, int pageSize);

    Thing getThingById(Integer thingId);
}
