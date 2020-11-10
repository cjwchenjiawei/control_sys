package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Worm;

public interface IWormService {
    PageInfo<Worm> listByPage(int pageNo, int pageSize, String pestName, String host);

    boolean insertSelective(Worm pest);
}
