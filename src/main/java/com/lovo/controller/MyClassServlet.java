package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lovo.model.Area;
import com.lovo.model.MyClass;
import com.lovo.service.IAreaService;
import com.lovo.service.IMyClassService;
import com.lovo.service.impl.AreaServiceImpl;
import com.lovo.service.impl.MyClassServiceImpl;
import com.lovo.util.MyUtils;
import com.lovo.vo.MyClassVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MyClassServlet",urlPatterns = "/MyClass.do")
public class MyClassServlet extends HttpServlet {
    IMyClassService cs = new MyClassServiceImpl();
    IAreaService as = new AreaServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method==null||method.equals("")){
            indexByPage(request,response);
        }else if (method.equalsIgnoreCase("addClass")){
            addClass(request,response);
        }else if (method.equalsIgnoreCase("updateClass")){
            updateClass(request,response);
        }else if (method.equalsIgnoreCase("queryClass")){
            queryClass(request,response);
        }else if (method.equalsIgnoreCase("queryClassList")){
            queryClassList(request,response);
        }
    }

    private void queryClassList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<MyClass> list = cs.queryClassList();
        out.write(MyUtils.toJSON(list));
    }

    private void queryClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer myClassId = Integer.parseInt(request.getParameter("myClassId"));
        Area a = as.queryById(myClassId);
        if (a.getMyClass() == null){
            out.write("该区域暂无相关小班负责");
            return;
        }
        String myClassName = a.getMyClass().getClassName();
        out.write(myClassName);
    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int classId = Integer.parseInt(request.getParameter("classId"));
        String classLeader = request.getParameter("classLeader");
        String classTel = request.getParameter("classTel");
        MyClass mc = new MyClass();
        mc.setClassId(classId);
        mc.setClassLeader(classLeader);
        mc.setClassTel(classTel);
        boolean flag = cs.updateByPrimaryKeySelective(mc);
        out.write(String.valueOf(flag));
    }

    private void addClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String className = request.getParameter("className");
        String classLeader = request.getParameter("classLeader");
        String classTel = request.getParameter("classTel");
        int classNumber = Integer.parseInt(request.getParameter("classNumber"));
        int areaId = Integer.parseInt(request.getParameter("areaName"));
        Area a = new Area();
        a.setAreaId(areaId);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String establishDate = format.format(date);

        MyClass mc = new MyClass(className,classLeader,classTel,classNumber,a,establishDate);
        boolean flag = cs.insertSelective(mc);
        out.write(String.valueOf(flag));
    }

    private void indexByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNo = 1;
        String p = request.getParameter("pageNo");
        if(p == null){
            pageNo = 1;
        }
        else{
            pageNo = Integer.parseInt(p);
        }
        String className = request.getParameter("className");
        String areaName = request.getParameter("areaName");
        PageInfo<MyClassVO> pageBean = cs.listByPage(pageNo,pageSize,className,areaName);
        Gson gson = new Gson();
        String json = gson.toJson(pageBean);
        out.write(json);
    }
}

