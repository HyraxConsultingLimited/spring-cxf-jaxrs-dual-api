package com.hyrax.cxf.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecureFilterTest {

    @Mock
    private HttpServletRequest mockHttpServletRequest;
    @Mock
    private HttpServletResponse mockHttpServletResponse;
    @Mock
    private FilterChain mockFilterChain;

    private SecureFilter secureFilter;

    @Before
    public void setUp() throws Exception {
        this.secureFilter = new SecureFilter();
    }

    @Test
    public void secureParamPresentAndCorrectDelegatesDownTheFilterChain() throws IOException, ServletException {

        when(mockHttpServletRequest.getParameter("SECURE")).thenReturn("Special Key");

        this.secureFilter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);

        verify(mockFilterChain).doFilter(mockHttpServletRequest, mockHttpServletResponse);
        verifyZeroInteractions(mockHttpServletResponse);
    }

    @Test
    public void secureParamPresentAndIncorrectRejectsTheRequest() throws IOException, ServletException {
        when(mockHttpServletRequest.getParameter("SECURE")).thenReturn("UNKNOWN");

        this.secureFilter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);

        verify(mockHttpServletResponse).setStatus(HttpStatus.FORBIDDEN.value());
        verifyZeroInteractions(mockFilterChain);
    }

    @Test
    public void secureParamMissingRejectsTheRequest() throws IOException, ServletException {
        when(mockHttpServletRequest.getParameter("SECURE")).thenReturn(null);

        this.secureFilter.doFilter(mockHttpServletRequest, mockHttpServletResponse, mockFilterChain);

        verify(mockHttpServletResponse).setStatus(HttpStatus.FORBIDDEN.value());
        verifyZeroInteractions(mockFilterChain);
    }
}