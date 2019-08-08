package com.cngc.boot.web.log;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于记录请求日志信息.
 *
 * @author max
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestLog {

    @AliasFor("message")
    String value() default "";

    @AliasFor("value")
    String message() default "";

    /**
     * 请求日志级别.
     *
     * @return RequestLogLevel
     */
    RequestLogEvent level() default RequestLogEvent.DEFAULT;
}
