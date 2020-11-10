package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.ConferMapper;
import com.lovo.model.Confer;
import com.lovo.model.Thing;
import com.lovo.service.IConferService;
import com.lovo.util.MyUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class ConferServiceImpl implements IConferService {
    @Override
    public boolean addConfer(Map<String, Object>map) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ConferMapper mapper = sqlSession.getMapper(ConferMapper.class);
        int n = mapper.insert(map);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public PageInfo<Confer> listByPage(int pageNo, int pageSize, Integer thingId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ConferMapper mapper = sqlSession.getMapper(ConferMapper.class);
        PageHelper.startPage(pageNo,pageSize);
        List<Confer> list = mapper.listByPage(thingId);
        PageInfo<Confer>pageInfo = new PageInfo<>(list);
        sqlSession.commit();
        sqlSession.close();
        return pageInfo;
    }
}
