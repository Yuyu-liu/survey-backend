package com.survey.backend.domain.security.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomCsrfFilter extends OncePerRequestFilter {

    public static final String XSRF_NAME = "XSRF-TOKEN";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (request.getHeader(XSRF_NAME).equals("null")) {
            ((HttpServletResponse) response).setHeader(
                "XSRF-TOKEN", csrf.getToken());
        }
        filterChain.doFilter(request, response);

    }
}