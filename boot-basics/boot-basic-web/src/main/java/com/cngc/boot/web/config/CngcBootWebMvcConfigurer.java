package com.cngc.boot.web.config;

import com.cngc.boot.core.CngcResourceBundleMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * cngc-boot核心配置类.
 *
 * @author maxD
 */
@Configuration
public class CngcBootWebMvcConfigurer {

    /**
     * cngc-web所用消息的basename.
     */
    private final static String CNGC_MESSAGE_BASENAME = "cngcWebMessage";


    @Bean
    public CngcResourceBundleMessageSource cngcMessageSource() {
        CngcResourceBundleMessageSource resourceBundleMessageSource = new CngcResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(CNGC_MESSAGE_BASENAME);
        return resourceBundleMessageSource;
    }
}
