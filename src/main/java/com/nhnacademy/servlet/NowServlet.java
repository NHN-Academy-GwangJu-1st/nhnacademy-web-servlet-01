package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class NowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(new Date());
        String url = getServletContext().getInitParameter("url");

        resp.getWriter().println(url);

        Integer counter = Optional.ofNullable((Integer) getServletContext().getAttribute("counter"))
                .orElse(0);
        getServletContext().setAttribute("counter", ++counter);

        resp.getWriter().println(counter);

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("service() called");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        log.info("service() called");

        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("service() called");

        super.init(config);
    }
}
