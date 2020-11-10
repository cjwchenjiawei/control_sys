package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.ThingMapper;
import com.lovo.model.Area;
import com.lovo.model.Thing;
import com.lovo.service.IAreaService;
import com.lovo.service.IThingService;
import com.lovo.util.MyUtils;
import com.lovo.util.PageUtils;
import com.lovo.vo.ThingVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ThingServiceImpl implements IThingService {
    IAreaService as = new AreaServiceImpl();
    @Override
    public PageInfo<ThingVO> listByPage(int pageNo, int pageSize, String thingName, String thingStage, String areaName, String startTime, String endTime) {
        Map<String,Object> map = new HashMap<>();
        map.put("thingName",thingName);
        map.put("thingStage",thingStage);
        map.put("areaName",areaName);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        SqlSession sqlSession = MyUtils.getSqlSession();
        ThingMapper mapper = sqlSession.getMapper(ThingMapper.class);

        PageHelper.startPage(pageNo,pageSize);
        List<Thing>list = mapper.listByPage(map);
        PageInfo<Thing>thingPageInfo = new PageInfo<>(list);
        PageInfo<ThingVO>thingVOPageInfo = PageUtils.PageInfo2PageInfoVo(thingPageInfo);
        List<ThingVO>listVO = new ArrayList<>();
        for (Thing thing:list) {
            ThingVO thingVO = new ThingVO();
            try {
                BeanUtils.copyProperties(thingVO,thing);
                Integer areaId = thing.getArea().getAreaId();
                Area area = as.queryById(areaId);
                BeanUtils.copyProperty(thingVO,"areaName",area.getAreaName());
                String className = null;
                if (area.getMyClass() == null){
                    className = "该区域暂无相关小班负责";
                    BeanUtils.copyProperty(thingVO,"className",className);
                } else {
                    BeanUtils.copyProperty(thingVO,"className",area.getMyClass().getClassName());
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            listVO.add(thingVO);
        }
        thingVOPageInfo.setList(listVO);
        sqlSession.commit();
        sqlSession.close();
        return thingVOPageInfo;
    }

    @Override
    public boolean insert(Thing thing) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ThingMapper mapper = sqlSession.getMapper(ThingMapper.class);
        int n = mapper.insert(thing);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public boolean update(Thing thing) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ThingMapper mapper = sqlSession.getMapper(ThingMapper.class);
        int n = mapper.updateByPrimaryKeySelective(thing);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public PageInfo<ThingVO> conferList(int pageNo, int pageSize) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ThingMapper mapper = sqlSession.getMapper(ThingMapper.class);

        PageHelper.startPage(pageNo,pageSize);
        List<Thing>list = mapper.conferList();
        PageInfo<Thing>thingPageInfo = new PageInfo<>(list);
        PageInfo<ThingVO>thingVOPageInfo = PageUtils.PageInfo2PageInfoVo(thingPageInfo);
        List<ThingVO>listVO = new ArrayList<>();
        for (Thing thing:list) {
            ThingVO thingVO = new ThingVO();
            try {
                BeanUtils.copyProperties(thingVO,thing);
                BeanUtils.copyProperty(thingVO,"areaName",thing.getArea().getAreaName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            listVO.add(thingVO);
        }
        thingVOPageInfo.setList(listVO);
        sqlSession.commit();
        sqlSession.close();
        return thingVOPageInfo;
    }

    @Override
    public Thing getThingById(Integer thingId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        ThingMapper mapper = sqlSession.getMapper(ThingMapper.class);
        Thing thing = mapper.getThingById(thingId);
        sqlSession.commit();
        sqlSession.close();
        return thing;
    }
}
