package com.cngc.boot.integration.gmp.config;

import com.cngc.boot.integration.gmp.DictTranslateServiceImpl;
import com.cngc.boot.web.dictionary.service.DictTranslateService;
import org.jeecg.common.system.sdk.service.IAdminDictService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
