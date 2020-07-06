package com.cngc.boot.web.log.impl;

import com.cngc.boot.web.log.RequestLogInfo;
import com.cngc.boot.web.log.RequestLogSend;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;

public class RequestLogSendKafka implements RequestLogSend {

    @Autowired
    private Environment env;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void send(RequestLogInfo logInfo) throws Throwable {
        //从配置文件中"spring.kafka.topic"获取主题，若没有配置，则使用默认主题"request-log"
        String topic = env.getProperty("spring.kafka.topic") == null ? "request-log" : env.getProperty("spring.kafka.topic");
        kafkaTemplate.send(topic, new ObjectMapper().writeValueAsString(logInfo));
    }
}
