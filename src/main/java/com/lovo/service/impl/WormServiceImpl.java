package com.lovo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovo.dao.WormMapper;
import com.lovo.model.Worm;
import com.lovo.service.IWormService;
import com.lovo.util.MyUtils;
import com.lovo.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WormServiceImpl implements IWormService {
    @Override
    public PageInfo<Worm> listByPage(int pageNo, int pageSize, String pestName, String host) {
        Worm worm = new Worm();
        worm.setWormName(pestName);
        worm.setWormHost(host);
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        WormMapper mapper = sqlSession.getMapper(WormMapper.class);

        //注意：这句话一定要放在读取数据的前面，才能实现拦截
        PageHelper.startPage(pageNo,pageSize);
        List<Worm> worms = mapper.listByPage(worm);
        PageInfo<Worm> pageBean = new PageInfo<>(worms);
        sqlSession.commit();
        sqlSession.close();
        return pageBean;
    }

    @Override
    public boolean insertSelective(Worm worm) {
        SqlSession sqlSession = MyUtils.getSqlSession();
        WormMapper mapper = sqlSession.getMapper(WormMapper.class);
        int n = mapper.insertSelective(worm);
        sqlSession.commit();
        sqlSession.close();
        return n>0?true:false;
    }
}
