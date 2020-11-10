package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Disease;
import com.lovo.service.IDiseaseService;
import com.lovo.service.impl.DiseaseServiceImpl;
import com.lovo.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DiseaseServlet",urlPatterns = "/disease.do")
public class DiseaseServlet extends HttpServlet {
    IDiseaseService ds = new DiseaseServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null || method.equals("")) {
            indexByPage(request, response);
        } else if (method.equalsIgnoreCase("addDisease")) {
            addDisease(request, response);
        }
    }

    private void addDisease (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String diseaseName = request.getParameter("diseaseName");
        String diseaseSource = request.getParameter("diseaseSource");
        String diseaseSymptom = request.getParameter("diseaseSymptom");
        String diseaseLaw = request.getParameter("diseaseLaw");
        String diseaseImages = request.getParameter("diseaseImages");
        String diseaseDetriment = request.getParameter("diseaseDetriment");
        String diseaseControl = request.getParameter("diseaseControl");
        Disease d = new Disease(diseaseName,diseaseSource,diseaseSymptom,diseaseLaw,diseaseImages,diseaseControl,diseaseDetriment);
        boolean flag = ds.insertSelective(d);
        out.write(String.valueOf(flag));
    }

    private void indexByPage (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String diseaseName = request.getParameter("diseaseName");
        String diseaseSymptom = request.getParameter("diseaseSymptom");
        PageInfo<Disease>pageBean = ds.indexByPage(pageNo,pageSize,diseaseName,diseaseSymptom);
        out.write(MyUtils.toJSON(pageBean));
    }
}