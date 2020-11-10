package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.vo.OutwareHouseDrugVO;

public interface IOutDrugService {
    PageInfo<OutwareHouseDrugVO> listByPage(int pageNo, int pageSize, Integer outwarehouseId);

    boolean addOutDrug(Integer outwarehouseId, Integer drugId);

    boolean updateOutDrug(Integer outwarehouseDrugId, Integer outwarehouseDrugNumber);

    boolean deleteOutDrug(Integer outwarehouseDrugId);
}
