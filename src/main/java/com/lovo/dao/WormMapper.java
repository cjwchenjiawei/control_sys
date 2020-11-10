package com.lovo.dao;

import com.lovo.Provider.WormProvider;
import com.lovo.model.Worm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface WormMapper {
    int deleteByPrimaryKey(Integer wormId);

    int insert(Worm record);

    Worm selectByPrimaryKey(Integer wormId);

    int updateByPrimaryKeySelective(Worm record);

    int updateByPrimaryKey(Worm record);

    /**
     * 分页 模糊查询（可选） 所有害虫信息
     * @param worm 模糊查询条件
     * @return
     */
    @SelectProvider(type = WormProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.WormMapper.BaseResultMap")
    List<Worm> listByPage (Worm worm);

    /**
     * 添加害虫
     * @param  record 待添加害虫信息
     * @return
     */
    @Insert("insert into t_worm (worm_name, worm_host, worm_breed, worm_enemy, worm_larvae, worm_adult, worm_control, worm_detriment) values(#{wormName},#{wormHost},#{wormBreed},#{wormEnemy},#{wormLarvae},#{wormAdult},#{wormControl},#{wormDetriment})")
    int insertSelective(Worm record);
}