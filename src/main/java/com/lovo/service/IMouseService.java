package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Disease;
import com.lovo.model.Mouse;

public interface IMouseService {
    PageInfo<Mouse> indexByPage(int pageNo, int pageSize, String mouseName);

    boolean addmouse(Mouse m);
}
