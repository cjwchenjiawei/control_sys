package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.DiseaseMapper;
import com.lovo.model.Disease;
import com.lovo.service.IDiseaseService;
import com.lovo.util.MyUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DiseaseServiceImpl implements IDiseaseService {
    @Override
    public PageInfo<Disease> indexByPage(int pageNo, int pageSize, String diseaseName, String diseaseSymptom) {
        Disease d = new Disease();
        d.setDiseaseName(diseaseName);
        d.setDiseaseSymptom(diseaseSymptom);
        SqlSession sqlSession = MyUtils.getSqlSession();
        DiseaseMapper mapper = sqlSession.getMapper(DiseaseMapper.class);

        PageHelper.startPage(pageNo,pageSize);
        List<Disease> diseases = mapper.listByPage(d);
        PageInfo<Disease>pageBean = new PageInfo<>(diseases);
        sqlSession.commit();
        sqlSession.close();
        return pageBean;
    }

    @Override
    public boolean insertSelective(Disease d) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        DiseaseMapper mapper = sqlSession.getMapper(DiseaseMapper.class);
        int n = mapper.insertSelective(d);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }
}
