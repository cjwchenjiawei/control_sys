package com.lovo.Provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ThingProvider {
    public String selectSQL(Map<String,Object> map){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_thing");
                INNER_JOIN("t_area on t_thing.fk_area_id=t_area.area_id");
                if (map.get("thingName") != null&&!map.get("thingName").equals("")){
                    AND().WHERE("t_thing.thing_name like '%${thingName}%'");
                }
                if (map.get("thingStage")!=null&&!map.get("thingStage").equals("")){
                    AND().WHERE("t_thing.thing_stage like '%${thingStage}%'");
                }
                if (map.get("areaName") != null&&!map.get("areaName").equals("")){
                    AND().WHERE("t_area.area_name like '%${areaName}%'");
                }
                if (map.get("startTime")!=null&&!map.get("startTime").equals("")){
                    AND().WHERE("t_thing.thing_time >= #{startTime}");
                }
                if (map.get("endTime")!=null&&!map.get("endTime").equals("")){
                    AND().WHERE("t_thing.thing_time <= #{endTime}");
                }
            }
        }.toString();
    }
}
