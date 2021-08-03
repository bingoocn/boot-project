package com.cngc.boot.integration.gmp;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * url查询参数提取token.
 *
 * @author maxD
 */
public class UrlTokenConverterFilter implements Filter {
    private static final String AUTH_HEADER_NAME = "Authorization";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String queryToken;
        if (!StringUtils.hasLength(httpServletRequest.getHeader(AUTH_HEADER_NAME)) &&
                StringUtils.hasLength(queryToken = httpServletRequest.getParameter("__token__"))) {
            final String token = OAuth2AccessToken.BEARER_TYPE.toLowerCase() + " " + queryToken;
            chain.doFilter(new HttpServletRequestWrapper(httpServletRequest) {
                @Override
                public Enumeration<String> getHeaders(String name) {
                    if (AUTH_HEADER_NAME.equals(name)) {
                        List<String> headers = new ArrayList<>();
                        headers.add(token);
                        return Collections.enumeration(headers);
                    }
                    return super.getHeaders(name);
                }

                @Override
                public String getHeader(String name) {
                    if (AUTH_HEADER_NAME.equals(name)) {
                        return token;
                    }
                    return super.getHeader(name);
                }

                @Override
                public Enumeration getHeaderNames() {
                    List<String> names = Collections.list(super.getHeaderNames());
                    names.add(AUTH_HEADER_NAME);
                    return Collections.enumeration(names);
                }
            }, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
