package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Area;
import com.lovo.model.Thing;
import com.lovo.service.IThingService;
import com.lovo.service.impl.ThingServiceImpl;
import com.lovo.util.MyUtils;
import com.lovo.vo.ThingVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;

@WebServlet(name = "ThingServlet",urlPatterns = "/thing.do")
public class ThingServlet extends HttpServlet {
    IThingService ts = new ThingServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method==null||method.equals("")){
            indexByPage(request,response);
        }else if (method.equalsIgnoreCase("addThing")){
            addThing(request,response);
        }else if (method.equalsIgnoreCase("updateThing")){
            updateThing(request,response);
        }else if (method.equalsIgnoreCase("conferList")){
            conferList(request,response);
        }
    }

    private void updateThing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String thingId = request.getParameter("thingId");
        String thingStage = request.getParameter("thingStage");
        String thingControl = request.getParameter("thingControl");
        Thing thing = new Thing();
        thing.setThingId(Integer.parseInt(thingId));
        thing.setThingStage(thingStage);
        thing.setThingControl(thingControl);
        boolean flag = ts.update(thing);
        out.write(String.valueOf(flag));
    }

    private void addThing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String thingName = request.getParameter("thingName");
        String thingImages = request.getParameter("thingImages");
        String thingTime = request.getParameter("thingTime");
        String thingType = request.getParameter("thingType");
        String thingStage = request.getParameter("thingStage");
        String thingDescription = request.getParameter("thingDescription");
        String thingDiscovery = request.getParameter("thingDiscovery");
        int areaId = Integer.parseInt(request.getParameter("areaName"));
        String thingLoss = request.getParameter("thingLoss");
        String thingArea = request.getParameter("thingArea");
        String thingControl = request.getParameter("thingControl");
        Area area = new Area();
        area.setAreaId(areaId);
        Thing thing = new Thing(thingName,thingImages,thingTime,thingType,thingStage,thingDescription,thingDiscovery,area,thingLoss,thingArea,thingControl);
        boolean flag = ts.insert(thing);
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
        String thingName = request.getParameter("thingName");
        String thingStage = request.getParameter("thingStage");
        String areaName = request.getParameter("areaName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        PageInfo<ThingVO>pageBean = ts.listByPage(pageNo,pageSize,thingName,thingStage,areaName,startTime,endTime);
        out.write(MyUtils.toJSON(pageBean));
    }

    private void conferList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        PageInfo<ThingVO>pageBean = ts.conferList(pageNo,pageSize);

        out.write(MyUtils.toJSON(pageBean));
    }

}
