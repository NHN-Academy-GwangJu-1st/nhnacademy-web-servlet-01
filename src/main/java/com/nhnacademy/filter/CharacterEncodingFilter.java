package com.nhnacademy.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CharacterEncodingFilter implements Filter {

    private String encoding;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("doFilter() called");

        request.setCharacterEncoding(this.encoding);
        chain.doFilter(request, response);

        log.info("chain.doFilter(request, response) called");
    }

}
