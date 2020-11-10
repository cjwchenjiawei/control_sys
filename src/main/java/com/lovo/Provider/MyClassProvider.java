package com.lovo.Provider;

import com.lovo.model.MyClass;
import com.lovo.model.Worm;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class MyClassProvider {

    public String selectBySelectiveProvider(Map<String,Object> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_class");
                INNER_JOIN("t_area on t_class.class_id=t_area.area_id");
                if (map.get("className") != null&&!map.get("className").equals("")){
                    AND().WHERE("t_class.class_name like '%${className}%'");
                }
                if (map.get("areaName") != null&&!map.get("areaName").equals("")){
                    AND().WHERE("t_area.area_name like concat('%',#{areaName},'%')");
                }
            }
        }.toString();
    }

    public String updateBySelectiveProvider(MyClass mc){
        return new SQL(){
            {
                UPDATE("t_class");
                if(mc.getClassLeader() != null){
                    SET("class_leader = #{classLeader}");
                }
                if (mc.getClassTel() != null) {
                    SET("class_tel = #{classTel}");
                }
                WHERE("class_id = #{classId}");
            }
        }.toString();
    }
}
