package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class BeautifyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String html = req.getParameter("html");

        resp.setContentType("text/plain");
        resp.getWriter().println(Jsoup.parse(html));
        String url = getServletContext().getInitParameter("url");

        resp.getWriter().println(url);
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getMethod().equals("POST")) {
            doPost(req, resp);
        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init() throws ServletException {
    }
}
