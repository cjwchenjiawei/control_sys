package com.lovo.dao;

import com.lovo.Provider.DrugProvider;
import com.lovo.model.Drug;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface DrugMapper {
    int deleteByPrimaryKey(Integer drugId);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(Integer drugId);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKey(Drug record);

    /**
     * 分页 模糊查询（可选） 医疗器械信息
     * @param drug 模糊查询条件
     * @return
     */
    @SelectProvider(type = DrugProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.DrugMapper.BaseResultMap")
    List<Drug> listByPage(Drug drug);

    /**
     * 添加医疗器械
     * @param record 待添加的医疗器械
     * @return
     */
    @Insert("insert into t_drug (drug_name, drug_control, drug_type, drug_usage) " +
            "values(#{drugName},#{drugControl},#{drugType},#{drugUsage})")
    int insert(Drug record);
}