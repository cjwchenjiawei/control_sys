package com.lovo.dao;

import com.lovo.Provider.ConferProvider;
import com.lovo.model.Confer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ConferMapper {
    int deleteByPrimaryKey(Integer conferId);

    int insertSelective(Confer record);

    Confer selectByPrimaryKey(Integer conferId);

    int updateByPrimaryKeySelective(Confer record);

    int updateByPrimaryKey(Confer record);

    /**
     * 添加会商
     * @param map 包含事件id、所有会商人员、会商结果、会商时间
     * @return
     */
    @Insert("insert into t_confer (fk_thing_id, confer_proficients, confer_result, confer_time) " +
            "values (#{thingId},#{conferProficients},#{conferResult},#{conferTime})")
    int insert(Map<String, Object> map);

    /**
     * 分页并且根据事件ID 查询会商信息
     * @param thingId
     * @return
     */
    @Select("select * from t_confer where fk_thing_id = #{thingId}")
    @ResultMap("com.lovo.mapper.ConferMapper.BaseResultMap")
    List<Confer> listByPage(Integer thingId);
}