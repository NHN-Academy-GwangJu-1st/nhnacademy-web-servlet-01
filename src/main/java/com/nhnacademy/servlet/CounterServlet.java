package com.nhnacademy.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CounterServlet extends HttpServlet {

    private static int countNum;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(countNum++);
        String url = getServletContext().getInitParameter("url");

        resp.getWriter().println(url);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String counter = config.getInitParameter("counter");
        countNum = Integer.parseInt(counter);
    }
}
