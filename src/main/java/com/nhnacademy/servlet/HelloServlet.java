package com.nhnacademy.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = getServletConfig().getInitParameter("title");
        String name = getServletConfig().getInitParameter("name");

        String url = getServletContext().getInitParameter("url");

        resp.getWriter().println("Hello " + title + name);
        resp.getWriter().println(url);
    }



}
