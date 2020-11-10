package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Disease;
import com.lovo.model.Mouse;
import com.lovo.service.IMouseService;
import com.lovo.service.impl.MouseServiceImpl;
import com.lovo.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MouseServlet",urlPatterns = "/mouse.do")
public class MouseServlet extends HttpServlet {
    IMouseService ms = new MouseServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null || method.equals("")) {
            indexByPage(request, response);
        } else if (method.equalsIgnoreCase("addMouse")) {
            addMouse(request, response);
        }
    }

    private void addMouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mouseName = request.getParameter("mouseName");
        String mouseBreed = request.getParameter("mouseBreed");
        String mouseFood = request.getParameter("mouseFood");
        String mouseImages = request.getParameter("mouseImages");
        String mouseEnemy = request.getParameter("mouseEnemy");
        String mouseDetriment = request.getParameter("mouseDetriment");
        String mouseControl = request.getParameter("mouseControl");
        Mouse m = new Mouse(mouseName,mouseFood,mouseBreed,mouseEnemy,mouseImages,mouseDetriment,mouseControl);
        boolean flag = ms.addmouse(m);
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
        String mouseName = request.getParameter("mouseName");
        PageInfo<Mouse> pageBean = ms.indexByPage(pageNo,pageSize,mouseName);
        out.write(MyUtils.toJSON(pageBean));

    }
}
