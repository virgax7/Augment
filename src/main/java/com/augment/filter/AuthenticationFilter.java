package com.augment.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "authenticationFilter", urlPatterns = { "*.xhtml", "/rest/*" })
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        try {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            final HttpSession session = httpServletRequest.getSession(false);
            final String requestURI = httpServletRequest.getRequestURI();

            if (passesFilter(session, requestURI)) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/account-pages/login.xhtml");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean passesFilter(final HttpSession session, final String requestURI) {
        return requestURI.contains("/login.xhtml") || requestURI.contains("/register.xhtml") || requestURI.contains("/reset-password.xhtml")
                || session != null && session.getAttribute("username") != null
                || requestURI.matches("/") || requestURI.contains("/index.xhtml")
                || requestURI.contains("javax.faces.resource");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}

