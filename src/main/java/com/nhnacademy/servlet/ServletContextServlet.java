package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Slf4j
public class ServletContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletConfig().getServletContext();


        Integer counter = Optional.ofNullable((Integer) getServletContext().getAttribute("counter"))
                .orElse(0);
        getServletContext().setAttribute("counter", ++counter);

        resp.getWriter().println(counter);

        resp.setContentType("text/plain");

        try {
            PrintWriter out = resp.getWriter();
            out.println(servletContext.getContextPath());
            out.println(servletContext.getMajorVersion());
            out.println(servletContext.getMinorVersion());
            out.println(servletContext.getEffectiveMajorVersion());
            out.println(servletContext.getEffectiveMinorVersion());
            out.println(servletContext.getRealPath("/servletcontext"));

        } catch (Exception e) {
            log.error("", e);
        }
        String url = getServletContext().getInitParameter("url");

        resp.getWriter().println(url);

    }

}
