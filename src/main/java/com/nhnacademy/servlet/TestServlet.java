package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello Servlet");


        Integer counter = Optional.ofNullable((Integer) getServletContext().getAttribute("counter"))
                .orElse(0);
        getServletContext().setAttribute("counter", ++counter);

        resp.getWriter().println(counter);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init() called");
        super.init(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("service() called");

        super.service(req, res);
    }

    @Override
    public void destroy() {
        log.info("destory() called");
        super.destroy();
    }
}
