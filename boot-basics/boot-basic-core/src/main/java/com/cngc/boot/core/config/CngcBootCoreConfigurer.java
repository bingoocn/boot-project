package com.cngc.boot.core.config;

import com.cngc.boot.core.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * cngc-boot核心配置类.
 *
 * @author maxD
 */
@Configuration
public class CngcBootCoreConfigurer {

    /**
     * 用于获取springcontext中的内容.
     *
     * @return spring上下文工具类
     */
    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }
}
