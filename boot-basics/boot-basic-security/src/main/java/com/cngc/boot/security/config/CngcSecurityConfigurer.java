package com.cngc.boot.security.config;

import com.cngc.boot.core.CngcResourceBundleMessageSource;
import com.cngc.boot.security.SecurityExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * cngc-security主配置类.
 *
 * @author maxD
 */
@Configuration
@Import(SecurityExceptionHandler.class)
public class CngcSecurityConfigurer {
    /**
     * cngc-security所用消息的basename.
     */
    private final static String CNGC_SECURITY_MESSAGE_BASENAME = "cngcSecurityMessage";


    @Bean
    public CngcResourceBundleMessageSource cngcSecurityMessageSource() {
        CngcResourceBundleMessageSource resourceBundleMessageSource = new CngcResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(CNGC_SECURITY_MESSAGE_BASENAME);
        return resourceBundleMessageSource;
    }
}
