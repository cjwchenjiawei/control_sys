package com.lovo.Provider;

import com.lovo.model.Worm;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class AreaProvider {
    public String selectiveProvider(Map<String,Object> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_area");
                LEFT_OUTER_JOIN("t_class on t_area.area_id = t_class.class_id");
                if(map.get("areaName") != null && !map.get("areaName").equals("")){
                    AND().WHERE("area_name like '%${areaName}%'");
                }
                if (map.get("areaForest") != null && !map.get("areaForest").equals("")) {
                    AND().WHERE("area_forest like '%${areaForest}%'");
                }
                if (map.get("myClassName") != null && !map.get("myClassName").equals("")) {
                    AND().WHERE("class_name like '%${myClassName}%'");
                }
            }
        }.toString();
    }

    public String queryAreaSQL(){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_area");
                LEFT_OUTER_JOIN("t_class on t_area.area_id = t_class.class_id");
                WHERE("t_class.class_id is null");
            }
        }.toString();
    }
}
