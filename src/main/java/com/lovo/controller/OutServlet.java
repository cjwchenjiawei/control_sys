package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.OutwareHouse;
import com.lovo.service.IOutService;
import com.lovo.service.impl.OutServiceImpl;
import com.lovo.util.MyUtils;
import com.lovo.vo.OutwareHouseVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OutServlet",urlPatterns = "/out.do")
public class OutServlet extends HttpServlet {
    IOutService os = new OutServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null || method.equals("")) {
            indexByPage(request, response);
        } else if (method.equalsIgnoreCase("addOut")) {
            addOut(request, response);
        }
    }

    private void addOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer classId = Integer.parseInt(request.getParameter("classId"));
        String userName = request.getParameter("userName");
        boolean flag = os.addOut(classId,userName);
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
        String myClassName = request.getParameter("myClassName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        PageInfo<OutwareHouseVO> pageInfo = os.listByPage(pageNo,pageSize,myClassName,startTime,endTime);
        out.write(MyUtils.toJSON(pageInfo));
    }
}
