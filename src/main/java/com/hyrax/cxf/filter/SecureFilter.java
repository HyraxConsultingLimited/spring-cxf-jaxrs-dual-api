package com.hyrax.cxf.filter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Component
public class SecureFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final String secureParam = servletRequest.getParameter("SECURE");
        if (secureParamIsPresentAndValid(secureParam)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

    private boolean secureParamIsPresentAndValid(String secureParam) {
        return secureParam != null && secureParam.equals("Special Key");
    }

    @Override
    public void destroy() {

    }
}
