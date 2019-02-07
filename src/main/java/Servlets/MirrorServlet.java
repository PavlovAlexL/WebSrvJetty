package Servlets;

import Templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MirrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> values = new HashMap<>();
        values.put("key", req.getParameterMap().toString());
        //resp.getWriter().println(req.getParameter("key"));
        resp.getWriter().println(values.get("key").toString());
        //resp.getWriter().println(PageGenerator.instance().getPage("mirror.html", values));
        //resp.getWriter().println(PageGenerator.instance().getPage("mirror.html", values));
        //resp.setContentType("text/html;charset=utf-8"); //обязательно вернуть ContextType
        //resp.setStatus(HttpServletResponse.SC_OK); //обязательно вернуть ключ http-ответа SC_OK = 200
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

}
