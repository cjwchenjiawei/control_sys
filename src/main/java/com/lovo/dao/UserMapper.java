package com.lovo.dao;

import com.lovo.Provider.UserProvider;
import com.lovo.model.User;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);

    /**
     * 分页 模糊查询（可选） 所有用户信息
     * @param user 模糊查询条件
     * @return
     */
    @SelectProvider(type = UserProvider.class,method = "selectSQL")
    @ResultMap("com.lovo.mapper.UserMapper.BaseResultMap")
    List<User> listByPage(User user);

    /**
     * 添加用户
     * @param record 待添加的用户信息
     * @return
     */
    @Insert("insert into t_user (user_account, user_password, user_name, user_grade) " +
            "values(#{userAccount},#{userPassword},#{userName},#{userGrade})")
    int insert(User record);

    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return
     */
    @Delete("delete from t_user where user_id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 更新用户信息
     * @param record 待更新的用户信息
     * @return
     */
    @Update("update t_user set user_password = #{userPassword},user_grade = #{userGrade} where user_id = #{userId}")
    int updateByPrimaryKeySelective(User record);

}