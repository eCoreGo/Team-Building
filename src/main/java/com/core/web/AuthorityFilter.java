package com.core.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorityFilter implements Filter {

    private FilterConfig config;
    private static final String SESSION_USER_KEY = "USER";

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        Object user = session.getAttribute(SESSION_USER_KEY);
        if (user != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String redirectURL = this.config.getInitParameter("redirectPath");
            response.sendRedirect(request.getContextPath() + redirectURL);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

}
