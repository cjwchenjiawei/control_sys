package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Proficient;
import com.lovo.service.IProficientService;
import com.lovo.service.impl.ProficientServiceImpl;
import com.lovo.util.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProficientServlet",urlPatterns = "/proficient.do")
public class ProficientServlet extends HttpServlet {
    IProficientService ps = new ProficientServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method==null||method.equals("")){
            indexByPage(request,response);
        }else if (method.equalsIgnoreCase("addProficient")){
            addProficient(request,response);
        }else if (method.equalsIgnoreCase("updateProficient")){
            updateProficient(request,response);
        }else if (method.equalsIgnoreCase("deleteProficient")){
            deleteProficient(request,response);
        }else if (method.equalsIgnoreCase("proficientList")){
            proficientList(request,response);
        }

    }

    private void proficientList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<Proficient> list = ps.proficientList();
        String json = MyUtils.toJSON(list);
        out.write(json);
    }

    private void deleteProficient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String proficientId = request.getParameter("proficientId");
        boolean flag = ps.delete(Integer.parseInt(proficientId));
        out.write(String.valueOf(flag));
    }

    private void updateProficient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer proficientId = Integer.parseInt(request.getParameter("proficientId"));
        String proficientJob = request.getParameter("proficientJob");
        String proficientTel = request.getParameter("proficientTel");
        String proficientUnit = request.getParameter("proficientUnit");
        String proficientEmail = request.getParameter("proficientEmail");
        Proficient proficient = new Proficient(proficientId,proficientJob,proficientTel,proficientUnit,proficientEmail);
        boolean flag = ps.update(proficient);
        out.write(String.valueOf(flag));
    }

    private void addProficient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String proficientName = request.getParameter("proficientName");
        String proficientGender = request.getParameter("proficientGender");
        String proficientBirthday = request.getParameter("proficientBirthday");
        String proficientImages = request.getParameter("proficientImages");
        String proficientSpeciality = request.getParameter("proficientSpeciality");
        String proficientJob = request.getParameter("proficientJob");
        String proficientTel = request.getParameter("proficientTel");
        String proficientUnit = request.getParameter("proficientUnit");
        String proficientAddress = request.getParameter("proficientAddress");
        String proficientEmail = request.getParameter("proficientEmail");
        Proficient proficient = new Proficient(proficientName,proficientGender,proficientBirthday,proficientImages,proficientSpeciality,proficientJob,proficientTel,proficientUnit,proficientAddress,proficientEmail);
        boolean flag = ps.insert(proficient);
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
        String proficientName = request.getParameter("proficientName");
        String proficientSpeciality = request.getParameter("proficientSpeciality");
        String proficientUnit = request.getParameter("proficientUnit");
        PageInfo<Proficient>pageInfo = ps.listByPage(pageNo,pageSize,proficientName,proficientSpeciality,proficientUnit);
        out.write(MyUtils.toJSON(pageInfo));
    }
}
