package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.service.IOutDrugService;
import com.lovo.service.impl.OutDrugServiceImpl;
import com.lovo.util.MyUtils;
import com.lovo.vo.OutwareHouseDrugVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OutDrugServlet",urlPatterns = "/outDrug.do")
public class OutDrugServlet extends HttpServlet {
    IOutDrugService ods = new OutDrugServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null || method.equals("")) {
            indexByPage(request, response);
        } else if (method.equalsIgnoreCase("addOutDrug")) {
            addOutDrug(request, response);
        } else if (method.equalsIgnoreCase("deleteOutDrug")) {
            deleteOutDrug(request, response);
        } else if (method.equalsIgnoreCase("updateOutDrug")) {
            updateOutDrug(request, response);
        }
    }

    private void updateOutDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer outwarehouseDrugId = Integer.parseInt(request.getParameter("outwarehouseDrugId"));
        Integer outwarehouseDrugNumber = Integer.parseInt(request.getParameter("outwarehouseDrugNumber"));
        boolean flag = ods.updateOutDrug(outwarehouseDrugId,outwarehouseDrugNumber);
        out.write(String.valueOf(flag));
    }


    private void deleteOutDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer outwarehouseDrugId = Integer.parseInt(request.getParameter("outwarehouseDrugId"));
        boolean flag = ods.deleteOutDrug(outwarehouseDrugId);
        out.write(String.valueOf(flag));
    }

    private void addOutDrug(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer outwarehouseId = Integer.parseInt(request.getParameter("outwarehouseId"));
        Integer drugId = Integer.parseInt(request.getParameter("drugId"));
        boolean flag = ods.addOutDrug(outwarehouseId,drugId);
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
        Integer outwarehouseId = Integer.parseInt(request.getParameter("outwarehouseId"));
        PageInfo<OutwareHouseDrugVO> pageInfo = ods.listByPage(pageNo,pageSize,outwarehouseId);
        out.write(MyUtils.toJSON(pageInfo));
    }
}
