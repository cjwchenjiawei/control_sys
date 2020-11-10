package com.lovo.util;


import com.google.gson.Gson;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyUtils {
    private static SqlSessionFactory factory ;

    static {
        String resources = "mybatis-configuration.xml";

        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resources);
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     *    <p>1. 在静态代码块中通过反射加载mybatis-configuration.xml获取配置文件的资源流</p>
     *    <p>static {</p>
     *          <p>&nbsp;&nbsp;&nbsp;&nbsp;String resources = "mybatis-configuration.xml";</p>
     *          <p>&nbsp;&nbsp;try {</p>
     *          <p>&nbsp;&nbsp;&nbsp;&nbsp;InputStream resource = Resources.getResourceAsStream(resources);</p>
     *          <p>&nbsp;&nbsp;&nbsp;&nbsp;factory = new SqlSessionFactoryBuilder().build(resource);</p>
     *          <p>&nbsp;&nbsp;} catch (IOException e) {</p>
     *          <p>&nbsp;&nbsp;&nbsp;&nbsp;e.printStackTrace();</p>
     *          <p>&nbsp;&nbsp;}</p>
     *    <p>}</p>
     *    <p>2. 通过 new SqlSessionFactoryBuilder().build(resource);获得一个SqlSession的工厂对象factory</p>
     *    <p>3. 最后通过 factory.openSession() 返回一个sqlSession对象</p>
     *
     * @return SqlSession对象
     */
    public static SqlSession getSqlSession() {
        return factory.openSession();
    }


    /**
     * 将传入对象转为JSON数据格式
     *
     * @param obj 被转的数据对象
     * @return 以字符串形式返回json格式数据
     */
    public static String toJSON(Object obj) {
        Gson json = new Gson();
        String data = json.toJson(obj);
        return data;
    }


    /**
     * 将符合日期模板的字符串转为date类型的数据返回
     *
     * @param src 传入的字符串
     * @return 返回字符串对应的Date数据
     */
    public static Date toDate(String src) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式
        Date date = null;
        try {
            date = sdf.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static LocalDate toLocalDate(String src) {
        LocalDate date = LocalDate.parse(src, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date;
    }


}
