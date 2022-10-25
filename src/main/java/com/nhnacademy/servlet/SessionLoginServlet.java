package com.nhnacademy.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "loginServlet", urlPatterns = "/login", initParams = {
        @WebInitParam(name= "id", value= "admin"),
        @WebInitParam(name = "password", value = "1234")
})
public class SessionLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String initId = getServletConfig().getInitParameter("id");
        String initPassword = getServletConfig().getInitParameter("password");

//        HttpSession session = req.getSession(false);
//        if (session == null) {
//            String id = req.getParameter("id");
//            String password = req.getParameter("password");
//
//            if (initId.equals(id) && initPassword.equals(password)) {
//                req.getSession().setAttribute("id", id);
//                resp.getWriter().println("login success!!");
//            } else {
//                resp.getWriter().println("login fail!!");
//            }
//        } else {
//            resp.getWriter().println("Already login");
//        }

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (initId.equals(id) && initPassword.equals(password)) {

            HttpSession session = req.getSession();
            session.setAttribute("id", id);

            resp.sendRedirect("/login");

            /* POST 요청이 계속 들어와서 500 error발생 */
            /* 이 요청을 끝내고 새로운 요청 ex) POST -> GET 일 경우는 resp.sendRedirect가 맞음 */

//            RequestDispatcher rd = req.getRequestDispatcher("/login");
//            rd.forward(req, resp);
        } else {
//            resp.sendRedirect("/login.html");
            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
            rd.forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (Objects.isNull(session)) {
//            resp.sendRedirect("/login.html");
            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
            rd.forward(req, resp);
        } else {
            resp.setContentType("text/html");
            resp.getWriter().println("login is Success!!");
            resp.getWriter().println("login id=" + session.getAttribute("id"));
            resp.getWriter().println("<a href='/logout'>로그아웃</a>");
        }
    }
}
