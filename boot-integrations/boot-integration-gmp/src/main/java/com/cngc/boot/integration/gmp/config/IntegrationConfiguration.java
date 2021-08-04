package com.cngc.boot.integration.gmp.config;

import com.cngc.boot.security.authentication.LoginUser;
import org.jeecg.common.system.sdk.GmpSdkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 集成配置类.
 *
 * @author maxD
 */
@Configuration
public class IntegrationConfiguration {

    @Bean
    public GmpSdkClient gmpSdkClient() {
        return new GmpSdkClient() {
            @Override
            public String getUserAccount() {
                LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return user.getAccount();
            }

            @Override
            public String getUserOrgCode() {
                LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return user.getOrgCode();
            }

            @Override
            public String getAppCode() {
                LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return user.getAppCode();
            }
        };
    }
}
