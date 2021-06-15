package com.cngc.boot.security.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * cngc-security web默认安全配置.
 *
 * @author maxD
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public abstract class CngcWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
}
