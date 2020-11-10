package com.lovo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * (懒汉)单例模式
 */
public class GetSqlSessionFactory {
    private static final Logger LOGGER = Logger.getLogger(GetSqlSessionFactory.class);
    private static SqlSessionFactory sqlSessionFactory;
    /**
     * 将构造方法私有化,这个类外部就不能实例化对象了
     */
    private GetSqlSessionFactory(){}

    /**
     * 使用同步锁,防止线程冲突
     * @return
     */
    public synchronized static SqlSessionFactory getSqlSessionFactory(){
        //如果sqlSessionFactory是null,就创建一个
        //也就是说如果是第一次调用这个类的这个方法sqlSessionFactory是null
        if (sqlSessionFactory == null) {
            //获取资源文件
            String resources = "mybatis-configuration.xml";
            InputStream is = null;

            try {
                is = Resources.getResourceAsStream(resources);
            } catch (IOException e) {
                LOGGER.error("获取资源失败",e);
            }

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        }
        return sqlSessionFactory;
    }

}
