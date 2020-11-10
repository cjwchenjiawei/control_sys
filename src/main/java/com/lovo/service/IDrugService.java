package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Drug;

public interface IDrugService {
    PageInfo<Drug> listByPage(int pageNo, int pageSize, String drugName, String drugControl, String drugType);

    boolean insert(Drug drug);
}
