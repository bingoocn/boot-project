package com.cngc.boot.integration.gmp;

import com.cngc.boot.security.authentication.CngcAuthenticationToken;
import com.cngc.boot.security.authentication.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 将{@link JwtAuthenticationToken}转换为{@link CngcAuthenticationToken}的过滤器.
 *
 * @author maxD
 */
public class CngcAuthenticationTokenConvertFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 不为jwt对应的token,不进行处理
        if (!(authentication instanceof JwtAuthenticationToken)) {
            filterChain.doFilter(request, response);
            return;
        }
        Jwt jwt = (Jwt) authentication.getPrincipal();
        LoginUser loginUser = LoginUser.withAccount(jwt.getSubject())
                .appCode(jwt.getAudience().get(0))
                .orgCode(jwt.getClaim("org_code"))
                .build();

        CngcAuthenticationToken cngcAuthenticationToken = new CngcAuthenticationToken(loginUser,
                authentication.getCredentials(), null);
        cngcAuthenticationToken.setAuthenticated(authentication.isAuthenticated());
        cngcAuthenticationToken.setDetails(authentication.getDetails());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(cngcAuthenticationToken);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }
}
