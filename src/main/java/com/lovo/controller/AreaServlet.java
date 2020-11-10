package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lovo.model.Area;
import com.lovo.service.IAreaService;
import com.lovo.service.impl.AreaServiceImpl;
import com.lovo.vo.AreaVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AreaServlet",urlPatterns = "/area.do")
public class AreaServlet extends HttpServlet {
    IAreaService as = new AreaServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method==null||method.equals("")){
            indexByPage(request,response);
        }else if (method.equalsIgnoreCase("addArea")){
            addArea(request,response);
        }else if (method.equalsIgnoreCase("queryAreaList")){
            queryAreaList(request,response);
        }else if (method.equalsIgnoreCase("queryAllAreaList")){
            queryAllAreaList(request,response);
        }
    }

    private void queryAllAreaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Area> list = as.queryAllAreaList();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        out.write(json);
    }

    private void queryAreaList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Area> list = as.queryAreaList();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        out.write(json);
    }

    private void addArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String areaName = request.getParameter("areaName");
        String areaForest = request.getParameter("areaForest");
        String areaLand = request.getParameter("areaLand");
        String areaTree = request.getParameter("areaTree");
        Area a = new Area(areaName,areaForest,areaTree,areaLand);
        boolean flag = as.insert(a);
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
        String areaName = request.getParameter("areaName");
        String areaForest = request.getParameter("areaForest");
        String myClassName = request.getParameter("myClass");
        PageInfo<AreaVO>pageBean = as.listByPage(pageNo,pageSize,areaName,areaForest,myClassName);
        Gson gson = new Gson();
        String json = gson.toJson(pageBean);

        out.write(json);
    }
}
