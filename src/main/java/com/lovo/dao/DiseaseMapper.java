package com.lovo.dao;

import com.lovo.Provider.DiseaseProvider;
import com.lovo.model.Disease;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface DiseaseMapper {
    int deleteByPrimaryKey(Integer diseaseId);

    int insert(Disease record);

    Disease selectByPrimaryKey(Integer diseaseId);

    int updateByPrimaryKeySelective(Disease record);

    int updateByPrimaryKey(Disease record);

    /**
     * 分页 模糊查询（可选） 病害信息
     * @param d 查询条件
     * @return
     */
    @SelectProvider(type = DiseaseProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.DiseaseMapper.BaseResultMap")
    List<Disease> listByPage(Disease d);

    /**
     * 添加病害
     * @param record 待添加的病害
     * @return
     */
    @Insert("insert into t_disease (disease_name, disease_source, disease_symptom, disease_law, disease_images,disease_control, disease_detriment) values (#{diseaseName},#{diseaseSource},#{diseaseSymptom},#{diseaseLaw},#{diseaseImages},#{diseaseControl},#{diseaseDetriment})")
    int insertSelective(Disease record);
}