package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.OutwareHouseDrugMapper;
import com.lovo.model.OutwareHouseDrug;
import com.lovo.service.IOutDrugService;
import com.lovo.util.MyUtils;
import com.lovo.util.PageUtils;
import com.lovo.vo.OutwareHouseDrugVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutDrugServiceImpl implements IOutDrugService {
    @Override
    public PageInfo<OutwareHouseDrugVO> listByPage(int pageNo, int pageSize, Integer outwarehouseId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        OutwareHouseDrugMapper mapper = sqlSession.getMapper(OutwareHouseDrugMapper.class);

        PageHelper.startPage(pageNo,pageSize);
        List<OutwareHouseDrug> list = mapper.listByPage(outwarehouseId);
        PageInfo<OutwareHouseDrug> outwareHouseDrugPageInfo = new PageInfo<>(list);
        PageInfo<OutwareHouseDrugVO> outwareHouseDrugVOPageInfo = PageUtils.PageInfo2PageInfoVo(outwareHouseDrugPageInfo);
        List<OutwareHouseDrugVO> listVO = new ArrayList<>();
        for (OutwareHouseDrug drug:list) {
            OutwareHouseDrugVO drugVO = new OutwareHouseDrugVO();
            try {
                BeanUtils.copyProperties(drugVO,drug);
                BeanUtils.copyProperty(drugVO,"drugName",drug.getDrugs().getDrugName());
                BeanUtils.copyProperty(drugVO,"drugType",drug.getDrugs().getDrugType());
                BeanUtils.copyProperty(drugVO,"drugControl",drug.getDrugs().getDrugControl());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            listVO.add(drugVO);
        }
        outwareHouseDrugVOPageInfo.setList(listVO);
        sqlSession.commit();
        sqlSession.close();
        return outwareHouseDrugVOPageInfo;
    }

    @Override
    public boolean addOutDrug(Integer outwarehouseId, Integer drugId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        OutwareHouseDrugMapper mapper = sqlSession.getMapper(OutwareHouseDrugMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("outwarehouseId",outwarehouseId);
        map.put("drugId",drugId);
        int n = mapper.insert(map);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public boolean updateOutDrug(Integer outwarehouseDrugId, Integer outwarehouseDrugNumber) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        OutwareHouseDrugMapper mapper = sqlSession.getMapper(OutwareHouseDrugMapper.class);
        OutwareHouseDrug outwareHouseDrug = new OutwareHouseDrug();
        outwareHouseDrug.setOutwarehouseDrugId(outwarehouseDrugId);
        outwareHouseDrug.setOutwarehouseDrugNumber(outwarehouseDrugNumber);
        int n = mapper.updateByPrimaryKey(outwareHouseDrug);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public boolean deleteOutDrug(Integer outwarehouseDrugId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        OutwareHouseDrugMapper mapper = sqlSession.getMapper(OutwareHouseDrugMapper.class);
        int n = mapper.deleteByPrimaryKey(outwarehouseDrugId);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }
}
