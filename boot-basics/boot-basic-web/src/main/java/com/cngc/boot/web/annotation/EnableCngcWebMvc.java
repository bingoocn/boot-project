package com.cngc.boot.web.annotation;


import com.cngc.boot.web.config.CngcBootWebMvcConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启cngc-web功能.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CngcBootWebMvcConfigurer.class)
public @interface EnableCngcWebMvc {
}
