package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Proficient;

import java.util.List;

public interface IProficientService {
    PageInfo<Proficient> listByPage(int pageNo, int pageSize, String proficientName, String proficientSpeciality, String proficientUnit);

    boolean insert(Proficient proficient);

    boolean delete(Integer proficientId);

    boolean update(Proficient proficient);

    List<Proficient> proficientList();
}
