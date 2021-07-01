package com.cngc.boot.integration.gmp.config;

import com.cngc.boot.integration.gmp.DictTranslateServiceImpl;
import com.cngc.boot.security.authentication.LoginUser;
import com.cngc.boot.web.dictionary.service.DictTranslateService;
import org.jeecg.common.system.sdk.GmpSdkClient;
import org.jeecg.common.system.sdk.service.IGmpDictService;
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
    public DictTranslateService dictTranslateService(IGmpDictService gmpDictService) {
        return new DictTranslateServiceImpl(gmpDictService);
    }

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
                //todo 获取当前登录用户的所属组织机构编码
                LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return "A01";
            }
        };
    }
}
