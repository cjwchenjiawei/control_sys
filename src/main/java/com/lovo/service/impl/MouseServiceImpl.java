package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.MouseMapper;
import com.lovo.model.Mouse;
import com.lovo.service.IMouseService;
import com.lovo.util.MyUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MouseServiceImpl implements IMouseService {
    @Override
    public PageInfo<Mouse> indexByPage(int pageNo, int pageSize, String mouseName) {
        Mouse m = new Mouse();
        m.setMouseName(mouseName);
        SqlSession sqlSession = MyUtils.getSqlSession();
        MouseMapper mapper = sqlSession.getMapper(MouseMapper.class);

        PageHelper.startPage(pageNo,pageSize);
        List<Mouse> mouses = mapper.listByPage(m);
        PageInfo<Mouse>pageBean = new PageInfo<>(mouses);
        sqlSession.commit();
        sqlSession.close();
        return pageBean;
    }

    @Override
    public boolean addmouse(Mouse m) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        MouseMapper mapper = sqlSession.getMapper(MouseMapper.class);
        int n = mapper.insert(m);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }
}
