package com.lovo.controller;

import com.github.pagehelper.PageInfo;
import com.lovo.model.Confer;
import com.lovo.model.Thing;
import com.lovo.service.IConferService;
import com.lovo.service.IThingService;
import com.lovo.service.impl.ConferServiceImpl;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ConferServlet",urlPatterns = "/confer.do")
public class ConferServlet extends HttpServlet {
    IConferService cs = new ConferServiceImpl();
    IThingService ts = new ThingServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method==null||method.equals("")){
            indexByPage(request,response);
        }else if (method.equalsIgnoreCase("addConfer")){
            addConfer(request,response);
        }
    }

    private void addConfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Integer thingId = Integer.parseInt(request.getParameter("thingId"));
        String conferProficients = request.getParameter("conferProficients");
        String conferResult = request.getParameter("conferResult");
        if ((conferProficients==null||conferProficients.equals(""))||(conferResult==null||conferResult.equals(""))){
            out.write("false");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("thingId",thingId);
        map.put("conferProficients",conferProficients);
        map.put("conferResult",conferResult);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        map.put("conferTime",format.format(date));

        boolean flag = cs.addConfer(map);
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
        Integer thingId = 0;
        String id = request.getParameter("thingId");
        if (id!=null&&!id.equals("")){
            thingId = Integer.parseInt(id);
        }
        PageInfo<Confer> list = cs.listByPage(pageNo,pageSize,thingId);
        out.write(MyUtils.toJSON(list));
    }
}
