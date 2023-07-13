package com.unoveo.securityjwt.calculator;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter("/")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse request=(HttpServletResponse) resp;
        request.setHeader("Access-Control-Allow-Origin", "*");

//      //  resp.setHeader("Access-Control-Allow-Origin", "*");
        request.setHeader("Access-Control-Allow-Methods", "POST");
        request.setHeader("Access-Control-Allow-Headers", "Content-Type");
        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {
    }
}
