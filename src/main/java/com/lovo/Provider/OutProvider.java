package com.lovo.Provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class OutProvider {
    public String selectSQL(Map<String,Object> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_outwarehouse");
                INNER_JOIN("t_class on t_outwarehouse.fk_class_id=t_class.class_id");
                if (map.get("myClassName") != null&&!map.get("myClassName").equals("")){
                    AND().WHERE("t_class.class_name like '%${myClassName}%'");
                }
                if (map.get("startTime")!=null&&!map.get("startTime").equals("")){
                    AND().WHERE("t_outwarehouse.outwarehouse_time >= #{startTime}");
                }
                if (map.get("endTime")!=null&&!map.get("endTime").equals("")){
                    AND().WHERE("t_outwarehouse.outwarehouse_time <= #{endTime}");
                }
            }
        }.toString();
    }
}
