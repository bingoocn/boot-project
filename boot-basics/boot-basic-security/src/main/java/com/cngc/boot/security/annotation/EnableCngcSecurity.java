package com.cngc.boot.security.annotation;

import com.cngc.boot.security.config.CngcSecurityConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启cngc-security功能.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CngcSecurityConfigurer.class)
public @interface EnableCngcSecurity {
}
