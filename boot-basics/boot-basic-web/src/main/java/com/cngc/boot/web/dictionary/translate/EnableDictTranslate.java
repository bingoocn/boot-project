package com.cngc.boot.web.dictionary.translate;

import com.cngc.boot.web.dictionary.translate.config.DictTranslateConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(DictTranslateConfig.class)
public @interface  EnableDictTranslate {
}
