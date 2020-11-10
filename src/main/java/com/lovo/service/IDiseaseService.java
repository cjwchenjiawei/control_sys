package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Disease;

public interface IDiseaseService {
    PageInfo<Disease> indexByPage(int pageNo, int pageSize, String diseaseName, String diseaseSymptom);

    boolean insertSelective(Disease d);
}
