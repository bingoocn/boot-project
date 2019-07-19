package com.cngc.boot.core.annotation;


import com.cngc.boot.core.config.CngcBootCoreConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启cngc-boot功能.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CngcBootCoreConfigurer.class)
public @interface EnableCngcBoot {
}
