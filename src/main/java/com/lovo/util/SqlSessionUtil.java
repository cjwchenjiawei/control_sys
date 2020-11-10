package com.lovo.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class SqlSessionUtil {
    private static final Logger LOGGER = Logger.getLogger(SqlSessionUtil.class);
    //为了线程安全,可以将SqlSession对象,放入到ThreadLocal对象中
    //ThreadLocal就是为了让线程更加安全
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    /**
     * 获取sqlSession对象
     * @return sqlSession
     */
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = tl.get();
        if(sqlSession == null){
            sqlSession = GetSqlSessionFactory.getSqlSessionFactory().openSession();
            tl.set(sqlSession);
        }
        LOGGER.info("sqlSession = " + sqlSession);
        return sqlSession;
    }

    /**
     * 提交事务
     */
    public static void commit(){
        SqlSession sqlSession = tl.get();
        if(sqlSession != null){
            sqlSession.commit();
            sqlSession.close();
            //当事务提交的时候,将tl里面保存的对象设置为空
            tl.set(null);
            LOGGER.info("mybaits sqlSession 提交事务");
        }
    }

    /**
     * 回滚事务
     */
    public static void rollback(){
        SqlSession sqlSession = tl.get();
        if(sqlSession != null){
            sqlSession.rollback();
            sqlSession.close();
            //当事务提交的时候,将tl里面保存的对象设置为空
            tl.set(null);
            LOGGER.info("mybaits sqlSession 回滚事务");
        }
    }
}
