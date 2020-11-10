package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lovo.model.Worm;
import com.lovo.service.IWormService;
import com.lovo.service.impl.WormServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PestServlet",urlPatterns = "/worm.do")
public class WormServlet extends HttpServlet {
    IWormService ws = new WormServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method==null||method.equalsIgnoreCase("")){
            indexByPage(request,response);
        }else if (method==null||method.equalsIgnoreCase("addWorm")){
            addWorm(request,response);
        }
    }

    private void addWorm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String wormName = request.getParameter("wormName");
        String host = request.getParameter("host");
        String breed = request.getParameter("breed");
        String enemy = request.getParameter("enemy");
        String larvaeImages = request.getParameter("larvaeImages");
        String adultImages = request.getParameter("adultImages");
        String detriment = request.getParameter("detriment");
        String control = request.getParameter("control");
        Worm worm = new Worm(wormName,host,breed,enemy,larvaeImages,adultImages,detriment,control);
        boolean flag = ws.insertSelective(worm);
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
        String wormName = request.getParameter("wormName");
        String host = request.getParameter("host");
        PageInfo<Worm> pageBean = ws.listByPage(pageNo, pageSize, wormName, host);
        Gson gson = new Gson();
        String json = gson.toJson(pageBean);
        out.write(json);
    }
}
