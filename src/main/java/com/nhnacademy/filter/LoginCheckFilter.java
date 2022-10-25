package com.nhnacademy.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class LoginCheckFilter implements Filter {

    private Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");

        excludeUrls = Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .collect(Collectors.toSet());

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("LoginFilter doFilter call");

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (Objects.isNull(session)) {
            // 세션이 없으면
            if (excludeUrls.contains(((HttpServletRequest) request).getRequestURI())) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/login.html");
            }

        } else {
            // 세션 있으면
            chain.doFilter(request, response);
        }

    }
}
