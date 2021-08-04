package com.cngc.boot.integration.gmp.autoconfigure;

import com.cngc.boot.integration.gmp.DictTranslateServiceImpl;
import com.cngc.boot.integration.gmp.config.IntegrationConfiguration;
import com.cngc.boot.web.dictionary.service.DictTranslateService;
import org.jeecg.common.system.sdk.service.IGmpDictService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gmp集成字典功能自动配置类.
 *
 * @author maxD
 */
@Configuration
@ConditionalOnBean(IntegrationConfiguration.class)
public class GmpIntegrationDictAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DictTranslateService dictTranslateService(IGmpDictService gmpDictService) {
        return new DictTranslateServiceImpl(gmpDictService);
    }
}
