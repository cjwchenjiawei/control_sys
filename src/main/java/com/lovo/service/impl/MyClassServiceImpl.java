package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.MyClassMapper;
import com.lovo.model.MyClass;
import com.lovo.service.IMyClassService;
import com.lovo.util.MyUtils;
import com.lovo.util.PageUtils;
import com.lovo.vo.MyClassVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClassServiceImpl implements IMyClassService {
    @Override
    public PageInfo<MyClassVO> listByPage(int pageNo, int pageSize, String className, String areaName) {
        Map<String,Object> map = new HashMap<>();
        map.put("className",className);
        map.put("areaName",areaName);
        SqlSession sqlSession = MyUtils.getSqlSession();
        MyClassMapper mapper = sqlSession.getMapper(MyClassMapper.class);

        //注意：这句话一定要放在读取数据的前面，才能实现拦截
        PageHelper.startPage(pageNo,pageSize);
        List<MyClass> list = mapper.listByPage(map);
        PageInfo<MyClass>myClassPageInfo = new PageInfo<>(list);
        PageInfo<MyClassVO>myClassVOPageInfo = PageUtils.PageInfo2PageInfoVo(myClassPageInfo);
        List<MyClassVO> listVO = new ArrayList<>();
        for (MyClass mc : list) {
            MyClassVO mcVO = new MyClassVO();
            try {
                BeanUtils.copyProperties(mcVO,mc);
                BeanUtils.copyProperty(mcVO,"areaName",mc.getArea().getAreaName());
                BeanUtils.copyProperty(mcVO,"areaForest",mc.getArea().getAreaForest());
                BeanUtils.copyProperty(mcVO,"areaLand",mc.getArea().getAreaLand());
                BeanUtils.copyProperty(mcVO,"areaTree",mc.getArea().getAreaTree());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            listVO.add(mcVO);
        }
        myClassVOPageInfo.setList(listVO);
        sqlSession.commit();
        sqlSession.close();
        return myClassVOPageInfo;
    }

    @Override
    public boolean insertSelective(MyClass mc) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        MyClassMapper mapper = sqlSession.getMapper(MyClassMapper.class);
        int n = mapper.insertSelective(mc);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public boolean updateByPrimaryKeySelective(MyClass mc) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        MyClassMapper mapper = sqlSession.getMapper(MyClassMapper.class);
        int n = mapper.updateByPrimaryKeySelective(mc);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public MyClass queryClass(Integer myClassId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        MyClassMapper mapper = sqlSession.getMapper(MyClassMapper.class);
        MyClass mc = mapper.queryClass(myClassId);
        sqlSession.commit();
        sqlSession.close();
        return mc;
    }

    @Override
    public List<MyClass> queryClassList() {
        SqlSession sqlSession = MyUtils.getSqlSession();
        MyClassMapper mapper = sqlSession.getMapper(MyClassMapper.class);
        List<MyClass>list = mapper.queryClassList();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
