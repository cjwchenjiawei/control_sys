package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.DrugMapper;
import com.lovo.model.Drug;
import com.lovo.service.IDrugService;
import com.lovo.util.MyUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DrugServiceImpl implements IDrugService {
    @Override
    public PageInfo<Drug> listByPage(int pageNo, int pageSize, String drugName, String drugControl, String drugType) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        DrugMapper mapper = sqlSession.getMapper(DrugMapper.class);
        Drug drug = new Drug(drugName,drugControl,drugType);

        PageHelper.startPage(pageNo,pageSize);
        List<Drug> list = mapper.listByPage(drug);
        PageInfo<Drug>pageInfo = new PageInfo<>(list);

        sqlSession.commit();
        sqlSession.close();
        return pageInfo;
    }

    @Override
    public boolean insert(Drug drug) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        DrugMapper mapper = sqlSession.getMapper(DrugMapper.class);
        int n = mapper.insert(drug);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }
}
