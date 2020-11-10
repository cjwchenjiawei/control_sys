package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Drug;
import com.lovo.service.IDrugService;
import com.lovo.service.impl.DrugServiceImpl;
import com.lovo.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DrugServlet", urlPatterns = "/drug.do")
public class DrugServlet extends HttpServlet {
    IDrugService ds = new DrugServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null || method.equals("")) {
            indexByPage(request, response);
        } else if (method.equalsIgnoreCase("addDrug")) {
            addDrug(request, response);
        }
    }

    private void addDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String drugName = request.getParameter("drugName");
        String drugControl = request.getParameter("drugControl");
        String drugType = request.getParameter("drugType");
        String drugUsage = request.getParameter("drugUsage");
        Drug drug = new Drug(drugName,drugControl,drugType,drugUsage);
        boolean flag = ds.insert(drug);
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
        String drugName = request.getParameter("drugName");
        String drugControl = request.getParameter("drugControl");
        String drugType = request.getParameter("drugType");
        PageInfo<Drug> pageBean = ds.listByPage(pageNo, pageSize, drugName, drugControl, drugType);
        out.write(MyUtils.toJSON(pageBean));
    }
}
