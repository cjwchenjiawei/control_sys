package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.UserMapper;
import com.lovo.model.User;
import com.lovo.service.IUserService;
import com.lovo.util.MyUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements IUserService {
    @Override
    public PageInfo<User> listByPage(int pageNo, int pageSize, String userGrade) {
        User user = new User();
        user.setUserGrade(userGrade);
        SqlSession sqlSession = MyUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        PageHelper.startPage(pageNo,pageSize);
        List<User> list = mapper.listByPage(user);
        PageInfo<User>pageInfo = new PageInfo<>(list);
        sqlSession.commit();
        sqlSession.close();
        return pageInfo;
    }

    @Override
    public boolean insert(User u) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int n = mapper.insert(u);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public boolean delete(Integer userId) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int n = mapper.deleteByPrimaryKey(userId);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }

    @Override
    public boolean update(User user) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int n = mapper.updateByPrimaryKeySelective(user);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }
}
