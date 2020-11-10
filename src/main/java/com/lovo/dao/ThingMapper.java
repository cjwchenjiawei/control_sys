package com.lovo.dao;

import com.lovo.Provider.ThingProvider;
import com.lovo.model.Thing;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ThingMapper {
    int deleteByPrimaryKey(Integer thingId);

    int insertSelective(Thing record);

    Thing selectByPrimaryKey(Integer thingId);

    int updateByPrimaryKey(Thing record);

    /**
     * 分页 模糊查询（可选） 所有事件信息
     * @param map
     * @return
     */
    @SelectProvider(type = ThingProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.ThingMapper.BaseResultMap")
    List<Thing> listByPage(Map<String, Object> map);

    /**
     * 添加事件
     * @param record 待添加的事件
     * @return
     */
    @Insert("insert into t_thing (thing_name, thing_images, thing_time, thing_type, thing_stage, thing_description,thing_discovery, fk_area_id, thing_loss, thing_area, thing_control) values (#{thingName},#{thingImages},#{thingTime},#{thingType},#{thingStage},#{thingDescription},#{thingDiscovery},#{area.areaId},#{thingLoss},#{thingArea},#{thingControl})")
    int insert(Thing record);

    /**
     * 更新事件
     * @param record 待更新事件
     * @return
     */
    @Update("update t_thing set thing_stage = #{thingStage}, thing_control = #{thingControl} where thing_id = #{thingId}")
    int updateByPrimaryKeySelective(Thing record);

    /**
     * 查询所有需要专家会商的事件
     * @return
     */
    @Select("select * from t_thing inner join t_area on t_thing.fk_area_id = t_area.area_id where thing_stage = '无法解决，申请专家会商'")
    @ResultMap("com.lovo.mapper.ThingMapper.BaseResultMap")
    List<Thing> conferList();

    /**
     * 根据ID查询事件信息
     * @param thingId 事件ID
     * @return
     */
    @Select("select * from t_thing where thing_id = #{thingId}")
    @ResultMap("com.lovo.mapper.ThingMapper.BaseResultMap")
    Thing getThingById(Integer thingId);
}