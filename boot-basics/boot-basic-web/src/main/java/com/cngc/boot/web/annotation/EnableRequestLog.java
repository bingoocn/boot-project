package com.cngc.boot.web.annotation;

import com.cngc.boot.web.log.RequestLogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启访问日志记录.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({RequestLogAspect.class})
public @interface EnableRequestLog {
}
