package com.cngc.boot.integration.r1.portal.annotation;

import com.cngc.boot.integration.r1.portal.config.R1IntegrationConfigs;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启集成使用r1 portal.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(R1IntegrationConfigs.class)
public @interface EnableR1IntegrationAnnotation {
}
