package com.cngc.boot.web.autoconfigure;

import com.cngc.boot.web.log.RequestLogAspect;
import com.cngc.boot.web.log.RequestLogSend;
import com.cngc.boot.web.log.impl.RequestLogSendKafka;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志自动配置类.
 *
 * @author max
 */
@Configuration
@ConditionalOnBean(RequestLogAspect.class)
public class RequestLogAutoConfiguration {

    /**
     * 默认加载RequestLogSendKafka类，如果有其他实现类则不加载
     *
     * @return RequestLogSendKafka
     */
    @Bean
    @ConditionalOnMissingBean
    public RequestLogSend requestLogSendSource() {
        return new RequestLogSendKafka();
    }

}
