package com.cngc.boot.integration.gmp.config;

import com.cngc.boot.integration.gmp.DictTranslateServiceImpl;
import com.cngc.boot.security.authentication.LoginUser;
import com.cngc.boot.web.dictionary.service.DictTranslateService;
import org.jeecg.common.system.sdk.GmpSdkClient;
import org.jeecg.common.system.sdk.service.IAdminDictService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    @ConditionalOnMissingBean
    public DictTranslateService dictTranslateService(IAdminDictService adminDictService) {
        return new DictTranslateServiceImpl(adminDictService);
    }

    @Bean
    public GmpSdkClient gmpSdkClient() {
        return new GmpSdkClient() {
            @Override
            public String getUserAccount() {
                LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return user.getAccount();
            }
        };
    }
}
