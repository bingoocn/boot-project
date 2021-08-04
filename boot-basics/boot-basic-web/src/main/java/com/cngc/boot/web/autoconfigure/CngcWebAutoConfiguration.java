package com.cngc.boot.web.autoconfigure;

import com.cngc.boot.web.config.CngcBootWebMvcConfigurer;
import com.cngc.boot.web.dictionary.translate.config.DictTranslateConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * cngc-web自动配置类.
 *
 * @author maxD
 */
@Configuration
@Import({CngcBootWebMvcConfigurer.class, DictTranslateConfig.class})
public class CngcWebAutoConfiguration {

}
