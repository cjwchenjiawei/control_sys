package com.lovo.dao;

import com.lovo.Provider.MyClassProvider;
import com.lovo.Provider.WormProvider;
import com.lovo.model.MyClass;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface MyClassMapper {
    int insert(MyClass record);

    /**
     * 添加班级
     * @param record 待添加的班级
     * @return
     */
    @Insert("insert into t_class (class_id, class_name, class_leader,class_tel, class_number, class_date)values(#{area.areaId},#{className},#{classLeader},#{classTel},#{classNumber},#{classDate})")
    int insertSelective(MyClass record);

    /**
     * 分页 模糊查询（可选） 班级信息
     * @param map 模糊查询条件
     * @return
     */
    @SelectProvider(type = MyClassProvider.class,method = "selectBySelectiveProvider")
    @ResultMap("com.lovo.mapper.MyClassMapper.BaseResultMap")
    List<MyClass> listByPage(Map<String,Object> map);

    /**
     * 更新班级信息
     * @param record 待更新的班级信息
     * @return
     */
    @UpdateProvider(type = MyClassProvider.class, method = "updateBySelectiveProvider")
    @ResultMap("com.lovo.mapper.MyClassMapper.BaseResultMap")
    int updateByPrimaryKeySelective(MyClass record);

    /**
     * 根据id查询班级信息
     * @param myClassId 班级id
     * @return
     */
    @Select("select * from t_class where class_id = #{myClassId}")
    @ResultMap("com.lovo.mapper.MyClassMapper.BaseResultMap")
    MyClass queryClass(Integer myClassId);

    /**
     * 查询事件的负责小班
     * @return
     */
    @Select("select * from t_class inner join t_thing on t_thing.fk_area_id = t_class.class_id")
    @ResultMap("com.lovo.mapper.MyClassMapper.BaseResultMap")
    List<MyClass> queryClassList();
}