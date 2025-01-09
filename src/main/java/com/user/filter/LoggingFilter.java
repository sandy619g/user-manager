package com.user.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class LoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpServletRequest &&
                response instanceof HttpServletResponse httpServletResponse) {

            long startTime = System.currentTimeMillis();

            logger.info("Request: Method = {}, URI = {}, TIMESTAMP = {}",
                    httpServletRequest.getMethod(),
                    httpServletRequest.getRequestURI(),
                    LocalDateTime.now());

            chain.doFilter(request, response);

            long duration = System.currentTimeMillis() - startTime;
            logger.info("Response: Status = {}, Duration = {} ms",
                    httpServletResponse.getStatus(),
                    duration);
        } else {
            chain.doFilter(request, response);
        }
    }
}


