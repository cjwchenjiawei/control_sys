package com.lovo.dao;

import com.lovo.model.OutwareHouseDrug;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface OutwareHouseDrugMapper {

    int insertSelective(OutwareHouseDrug record);

    OutwareHouseDrug selectByPrimaryKey(Integer outwarehouseDrugId);

    int updateByPrimaryKeySelective(OutwareHouseDrug record);

    /**
     * 分页并且根据出库ID查询出库的物品信息
     * @param outwarehouseId 出库ID
     * @return
     */
    @Select("select * from t_outwarehouse_drug inner join t_drug on t_outwarehouse_drug.fk_drug_id = t_drug.drug_id where fk_outwarehouse_id = #{outwarehouseId}")
    @ResultMap("com.lovo.mapper.OutwareHouseDrugMapper.BaseResultMap")
    List<OutwareHouseDrug> listByPage(Integer outwarehouseId);

    /**
     * 添加出库的物品信息
     * @param map 添加条件
     * @return
     */
    @Insert("insert into t_outwarehouse_drug (fk_outwarehouse_id, fk_drug_id)" +
            " values (#{outwarehouseId},#{drugId})")
    int insert(Map<String,Object> map);

    /**
     * 更新物品信息
     * @param record 待更新的物品
     * @return
     */
    @Update("update t_outwarehouse_drug set outwarehouse_drug_number = #{outwarehouseDrugNumber} where outwarehouse_drug_id = #{outwarehouseDrugId}")
    int updateByPrimaryKey(OutwareHouseDrug record);

    /**
     * 删除物品信息
     * @param outwarehouseDrugId 物品ID
     * @return
     */
    @Delete("delete from t_outwarehouse_drug where outwarehouse_drug_id = #{outwarehouseDrugId}")
    int deleteByPrimaryKey(Integer outwarehouseDrugId);

}