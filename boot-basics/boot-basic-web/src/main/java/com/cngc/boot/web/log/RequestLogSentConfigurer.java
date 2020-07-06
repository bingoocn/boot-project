package com.cngc.boot.web.log;

import com.cngc.boot.web.log.impl.RequestLogSendKafka;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestLogSentConfigurer {

    /**
     * 默认加载RequestLogSendKafka类，如果有其他实现类则不加载
     * @return RequestLogSendKafka
     */
    @Bean
    @ConditionalOnMissingBean
    public RequestLogSend requestLogSendSource() {
        return new RequestLogSendKafka();
    }

}
