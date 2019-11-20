package com.cngc.boot.web.config;

import com.cngc.boot.core.CngcResourceBundleMessageSource;
import com.cngc.boot.web.ServletExceptionHandler;
import com.cngc.boot.web.log.RequestLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * cngc-boot核心配置类.
 *
 * @author maxD
 */
@Configuration
@Import({ServletExceptionHandler.class})
public class CngcBootWebMvcConfigurer {

    /**
     * cngc-web所用消息的basename.
     */
    private final static String CNGC_WEB_MESSAGE_BASENAME = "cngcWebMessage";


    @Bean
    public CngcResourceBundleMessageSource cngcWebMessageSource() {
        CngcResourceBundleMessageSource resourceBundleMessageSource = new CngcResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(CNGC_WEB_MESSAGE_BASENAME);
        return resourceBundleMessageSource;
    }
}
