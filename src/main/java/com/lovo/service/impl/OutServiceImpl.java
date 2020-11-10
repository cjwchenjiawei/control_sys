package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.OutwareHouseMapper;
import com.lovo.model.OutwareHouse;
import com.lovo.service.IOutService;
import com.lovo.util.MyUtils;
import com.lovo.util.PageUtils;
import com.lovo.vo.OutwareHouseVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OutServiceImpl implements IOutService {
    @Override
    public PageInfo<OutwareHouseVO> listByPage(int pageNo, int pageSize, String myClassName, String startTime, String endTime) {
        Map<String,Object>map = new HashMap<>();
        map.put("myClassName",myClassName);
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        SqlSession sqlSession = MyUtils.getSqlSession();
        OutwareHouseMapper mapper = sqlSession.getMapper(OutwareHouseMapper.class);
        PageHelper.startPage(pageNo,pageSize);
        List<OutwareHouse> list = mapper.listByPage(map);
        PageInfo<OutwareHouse> outwareHousePageInfo = new PageInfo<>(list);
        PageInfo<OutwareHouseVO> outwareHouseVOPageInfo = PageUtils.PageInfo2PageInfoVo(outwareHousePageInfo);
        List<OutwareHouseVO> listVO = new ArrayList<>();
        for (OutwareHouse out:list) {
            OutwareHouseVO outwareHouseVO = new OutwareHouseVO();
            try {
                BeanUtils.copyProperties(outwareHouseVO,out);
                BeanUtils.copyProperty(outwareHouseVO,"myClassName",out.getMyClass().getClassName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            listVO.add(outwareHouseVO);
        }
        outwareHouseVOPageInfo.setList(listVO);
        sqlSession.commit();
        sqlSession.close();
        return outwareHouseVOPageInfo;
    }

    @Override
    public boolean addOut(Integer classId, String userName) {
        Map<String, Object>map = new HashMap();
        map.put("classId",classId);
        map.put("userName",userName);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String outwareHouseTime = sdf.format(d);
        map.put("outwareHouseTime",outwareHouseTime);
        SqlSession sqlSession = MyUtils.getSqlSession();
        OutwareHouseMapper mapper = sqlSession.getMapper(OutwareHouseMapper.class);
        int n = mapper.insert(map);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }
}
