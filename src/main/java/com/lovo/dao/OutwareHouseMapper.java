package com.lovo.dao;

import com.lovo.Provider.OutProvider;
import com.lovo.model.OutwareHouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface OutwareHouseMapper {
    int deleteByPrimaryKey(Integer outwarehouseId);

    int insertSelective(OutwareHouse record);

    OutwareHouse selectByPrimaryKey(Integer outwarehouseId);

    int updateByPrimaryKeySelective(OutwareHouse record);

    int updateByPrimaryKey(OutwareHouse record);

    /**
     * 分页 模糊查询（可选） 所有出库信息
     * @param map 模糊查询条件
     * @return
     */
    @SelectProvider(type = OutProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.OutwareHouseMapper.BaseResultMap")
    List<OutwareHouse> listByPage(Map<String, Object> map);

    /**
     * 添加出库信息
     * @param map 添加信息
     * @return
     */
    @Insert("insert into t_outwarehouse (fk_class_id,outwarehouse_user,outwarehouse_time) " +
            " values (#{classId},#{userName},#{outwareHouseTime})")
    int insert(Map<String, Object>map);
}