package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.ProficientMapper;
import com.lovo.model.Proficient;
import com.lovo.service.IProficientService;
import com.lovo.util.MyUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProficientServiceImpl implements IProficientService {
    @Override
    public PageInfo<Proficient> listByPage(int pageNo, int pageSize, String proficientName, String proficientSpeciality, String proficientUnit) {
        Proficient p = new Proficient();
        p.setProficientName(proficientName);
        p.setProficientSpeciality(proficientSpeciality);
        p.setProficientUnit(proficientUnit);
        SqlSession sqlSession = MyUtils.getSqlSession();
        ProficientMapper mapper = sqlSession.getMapper(ProficientMapper.class);
        PageHelper.startPage(pageNo,pageSize);
        List<Proficient> list = mapper.listByPage(p);
        PageInfo<Proficient>pageInfo = new PageInfo<>(list);
        sqlSession.commit();
        sqlSession.close();
        return pageInfo;
    }

    @Override
    public boolean insert(Proficient proficient) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ProficientMapper mapper = sqlSession.getMapper(ProficientMapper.class);
        int insert = mapper.insert(proficient);
        sqlSession.commit();
        sqlSession.close();
        return insert>0?true:false;
    }

    @Override
    public boolean delete(Integer proficientId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ProficientMapper mapper = sqlSession.getMapper(ProficientMapper.class);
        int n = mapper.deleteByPrimaryKey(proficientId);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public boolean update(Proficient proficient) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ProficientMapper mapper = sqlSession.getMapper(ProficientMapper.class);
        int n = mapper.updateByPrimaryKeySelective(proficient);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public List<Proficient> proficientList() {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ProficientMapper mapper = sqlSession.getMapper(ProficientMapper.class);
        List<Proficient> list = mapper.proficientList();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
