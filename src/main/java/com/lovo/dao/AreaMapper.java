package com.lovo.dao;

import com.lovo.Provider.AreaProvider;
import com.lovo.model.Area;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface AreaMapper {
    int insert(Area record);

    /**
     * 添加区域
     * @param record 待添加的区域
     * @return
     */
    @Insert("insert into t_area (area_name, area_forest,area_tree, area_land) values(#{areaName},#{areaForest},#{areaTree},#{areaLand})")
    int insertSelective(Area record);

    /**
     * 分页并且模糊（可选）查询区域
     * @param map 模糊查询条件（可选）
     * @return
     */
    @SelectProvider(type = AreaProvider.class,method = "selectiveProvider")
    @ResultMap("com.lovo.mapper.AreaMapper.BaseResultMap")
    List<Area> listByPage (Map<String,Object> map);

    /**
     * 查询所有没有负责小班的区域
     * @return
     */
    @SelectProvider(type = AreaProvider.class,method = "queryAreaSQL")
    @ResultMap("com.lovo.mapper.AreaMapper.BaseResultMap")
    List<Area> queryAreaList();

    /**
     * 查询所有区域
     * @return
     */
    @Select("select * from t_area left join t_class on t_area.area_id = t_class.class_id")
    @ResultMap("com.lovo.mapper.AreaMapper.BaseResultMap")
    List<Area> queryAllAreaList();

    /**
     * 根据id查询区域
     * @param myClassId 班级id 区域表和班级表一对一关联
     * @return
     */
    @Select("select * from t_area left join t_class on t_area.area_id=t_class.class_id where t_area.area_id = #{myClassId}")
    @ResultMap("com.lovo.mapper.AreaMapper.BaseResultMap")
    Area queryById(Integer myClassId);
}