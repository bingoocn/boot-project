package com.cngc.boot.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 认证类.
 *
 * @author maxD
 */
public class CngcAuthenticationToken extends AbstractAuthenticationToken {
    private Object principal;
    private Object credentials;

    public CngcAuthenticationToken(LoginUser loginUser,
                                   Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = loginUser;
        this.credentials = credentials;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
