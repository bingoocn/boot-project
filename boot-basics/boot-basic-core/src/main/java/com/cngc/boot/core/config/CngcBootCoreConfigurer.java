package com.cngc.boot.core.config;

import com.cngc.boot.core.util.SpringContextUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * cngc-boot核心配置类.
 *
 * @author maxD
 */
@Configuration
@Import({SpringContextUtil.class})
public class CngcBootCoreConfigurer {
}
