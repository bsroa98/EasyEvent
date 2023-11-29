package com.ucatolica.easyevent.easyevent.security;

import jakarta.servlet.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class JsonRequestFilter implements Filter {


    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            if ("application/json".equals(httpRequest.getContentType())) {
                chain.doFilter(request, response);
            } else {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"error\": \"Only JSON requests are allowed\"}");
            }
        }

    }
}
