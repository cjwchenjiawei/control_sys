package com.lovo.dao;

import com.lovo.Provider.MouseProvider;
import com.lovo.model.Mouse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface MouseMapper {
    int deleteByPrimaryKey(Integer mouseId);

    int insertSelective(Mouse record);

    Mouse selectByPrimaryKey(Integer mouseId);

    int updateByPrimaryKeySelective(Mouse record);

    int updateByPrimaryKey(Mouse record);

    /**
     * 分页 模糊查询（可选） 鼠害信息
     * @param mouse 模糊查询条件
     * @return
     */
    @SelectProvider(type = MouseProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.MouseMapper.BaseResultMap")
    List<Mouse> listByPage(Mouse mouse);

    /**
     * 添加鼠害
     * @param record 待添加的鼠害
     * @return
     */
    @Insert("insert into t_mouse(mouse_name, mouse_food, mouse_breed, mouse_enemy, mouse_images, mouse_control,mouse_detriment)values(#{mouseName},#{mouseFood},#{mouseBreed},#{mouseEnemy},#{mouseImages},#{mouseControl},#{mouseDetriment})")
    int insert(Mouse record);
}