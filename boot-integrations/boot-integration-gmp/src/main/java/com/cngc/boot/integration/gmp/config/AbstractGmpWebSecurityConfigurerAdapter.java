package com.cngc.boot.integration.gmp.config;

import com.cngc.boot.integration.gmp.CngcAuthenticationTokenConvertFilter;
import com.cngc.boot.security.config.CngcWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;

/**
 * gmp平台web安全配置适配类.
 *
 * @author maxD
 */
public abstract class AbstractGmpWebSecurityConfigurerAdapter extends CngcWebSecurityConfigurerAdapter {
    @Override
    protected final void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CngcAuthenticationTokenConvertFilter(), BearerTokenAuthenticationFilter.class)
                .oauth2ResourceServer().jwt();
        configureExtend(http);
    }

    /**
     * 重写此方法以对HttpSecurity进行设置.
     *
     * @param http http安全配置类
     * @throws Exception 异常
     */
    protected abstract void configureExtend(HttpSecurity http) throws Exception;
}
