package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.User;
import com.lovo.service.IUserService;
import com.lovo.service.impl.UserServiceImpl;
import com.lovo.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet",urlPatterns = "/user.do")
public class UserServlet extends HttpServlet {
    IUserService us = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null || method.equals("")) {
            indexByPage(request, response);
        } else if (method.equalsIgnoreCase("addUser")) {
            addUser(request, response);
        } else if (method.equalsIgnoreCase("deleteUser")) {
            deleteUser(request, response);
        } else if (method.equalsIgnoreCase("updateUser")) {
            updateUser(request, response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String userPassword1 = request.getParameter("userPassword1");
        String userPassword2 = request.getParameter("userPassword2");
        String userGrade = request.getParameter("userGrade");
        if (!userPassword1.equals(userPassword2)){
            out.write("两次密码输入不一致，修改失败！");
            return;
        }
        User user = new User();
        user.setUserId(userId);
        user.setUserPassword(userPassword1);
        user.setUserGrade(userGrade);
        boolean flag = us.update(user);
        out.write(String.valueOf(flag));
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        boolean flag = us.delete(userId);
        out.write(String.valueOf(flag));
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String userAccount = request.getParameter("userAccount");
        String userPassword1 = request.getParameter("userPassword1");
        String userPassword2 = request.getParameter("userPassword2");
        String userName = request.getParameter("userName");
        String userGrade = request.getParameter("userGrade");
        if (!userPassword1.equals(userPassword2)){
            out.write("两次密码输入不一致，注册失败！");
            return;
        }
        User u = new User(userAccount,userPassword1,userName,userGrade);
        boolean flag = us.insert(u);
        out.write(String.valueOf(flag));
    }

    private void indexByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNo = 1;
        String p = request.getParameter("pageNo");
        if (p == null) {
            pageNo = 1;
        } else {
            pageNo = Integer.parseInt(p);
        }
        String userGrade = request.getParameter("userGrade");
        PageInfo<User> pageInfo = us.listByPage(pageNo, pageSize, userGrade);
        out.write(MyUtils.toJSON(pageInfo));
    }
}
