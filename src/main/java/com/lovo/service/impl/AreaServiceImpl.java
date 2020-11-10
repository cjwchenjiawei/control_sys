package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.AreaMapper;
import com.lovo.model.Area;
import com.lovo.service.IAreaService;
import com.lovo.util.MyUtils;
import com.lovo.util.PageUtils;
import com.lovo.vo.AreaVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaServiceImpl implements IAreaService {
    @Override
    public PageInfo<AreaVO> listByPage(int pageNo, int pageSize, String areaName, String areaForest, String myClassName) {
        Map<String,Object> map = new HashMap<>();
        map.put("areaName",areaName);
        map.put("areaForest",areaForest);
        map.put("myClassName",myClassName);

        SqlSession sqlSession = MyUtils.getSqlSession();
        AreaMapper mapper = sqlSession.getMapper(AreaMapper.class);

        PageHelper.startPage(pageNo,pageSize);
        List<Area> list = mapper.listByPage(map);
        PageInfo<Area>areaPageInfo = new PageInfo<>(list);
        PageInfo<AreaVO>areaVOPageInfo = PageUtils.PageInfo2PageInfoVo(areaPageInfo);
        List<AreaVO>listVO = new ArrayList<>();
        for (Area area : list) {
            AreaVO areaVO = new AreaVO();
            try {
                BeanUtils.copyProperties(areaVO,area);
                if (area.getMyClass()!=null){
                    BeanUtils.copyProperty(areaVO,"myClassName",area.getMyClass().getClassName());

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            listVO.add(areaVO);
        }
        areaVOPageInfo.setList(listVO);
        sqlSession.commit();
        sqlSession.close();
        return areaVOPageInfo;
    }

    @Override
    public boolean insert(Area a) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        AreaMapper mapper = sqlSession.getMapper(AreaMapper.class);
        int n = mapper.insertSelective(a);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public List<Area> queryAreaList() {
        SqlSession sqlSession = MyUtils.getSqlSession();
        AreaMapper mapper = sqlSession.getMapper(AreaMapper.class);
        List<Area> areas = mapper.queryAreaList();
        sqlSession.commit();
        sqlSession.close();
        return areas;
    }

    @Override
    public List<Area> queryAllAreaList() {
        SqlSession sqlSession = MyUtils.getSqlSession();
        AreaMapper mapper = sqlSession.getMapper(AreaMapper.class);
        List<Area> areas = mapper.queryAllAreaList();
        sqlSession.commit();
        sqlSession.close();
        return areas;
    }

    @Override
    public Area queryById(Integer myClassId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        AreaMapper mapper = sqlSession.getMapper(AreaMapper.class);
        Area a = mapper.queryById(myClassId);
        sqlSession.commit();
        sqlSession.close();
        return a;
    }
}
