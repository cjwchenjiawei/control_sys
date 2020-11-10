package com.lovo.dao;

import com.lovo.Provider.ProficientProvider;
import com.lovo.model.Proficient;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProficientMapper {

    int insertSelective(Proficient record);

    Proficient selectByPrimaryKey(Integer proficientId);

    int updateByPrimaryKey(Proficient record);

    /**
     * 分页 模糊查询（可选） 所有专家信息
     * @param p 模糊查询条件
     * @return
     */
    @SelectProvider(type = ProficientProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.ProficientMapper.BaseResultMap")
    List<Proficient> listByPage(Proficient p);

    /**
     *添加专家信息
     * @param record 待添加的专家信息
     * @return
     */
    @Insert("insert into t_proficient (proficient_name, proficient_gender, proficient_birthday, proficient_images, proficient_speciality, proficient_job, proficient_tel, proficient_unit, proficient_address, proficient_email) " +
            "values(#{proficientName},#{proficientGender},#{proficientBirthday},#{proficientImages},#{proficientSpeciality},#{proficientJob},#{proficientTel},#{proficientUnit},#{proficientAddress},#{proficientEmail})")
    int insert(Proficient record);

    /**
     * 根据ID删除专家信息
     * @param proficientId 专家ID
     * @return
     */
    @Delete("delete from t_proficient where proficient_id = #{proficientId}")
    int deleteByPrimaryKey(Integer proficientId);

    /**
     * 更新专家信息
     * @param record 待更新的专家信息
     * @return
     */
    @Update("update t_proficient set proficient_job = #{proficientJob},proficient_Tel = #{proficientTel} ,proficient_unit = #{proficientUnit},proficient_email = #{proficientEmail} where proficient_id = #{proficientId}")
    int updateByPrimaryKeySelective(Proficient record);

    /**
     * 查询所有专家信息
     * @return
     */
    @Select("select proficient_id, proficient_name from t_proficient ")
    @ResultMap("com.lovo.mapper.ProficientMapper.BaseResultMap")
    List<Proficient> proficientList();
}