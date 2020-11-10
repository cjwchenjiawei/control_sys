package com.lovo.service;

import com.github.pagehelper.PageInfo;
import com.lovo.model.User;

public interface IUserService {
    PageInfo<User> listByPage(int pageNo, int pageSize, String userGrade);

    boolean insert(User u);

    boolean delete(Integer userId);

    boolean update(User user);
}
