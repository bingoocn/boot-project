package com.cngc.boot.integration.mdm.annotation;

import com.cngc.boot.integration.mdm.config.MdmIntegrationConfigs;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:viking
 * @date:2019/4/8 16:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MdmIntegrationConfigs.class)
public @interface EnableMdmIntegrationAnnotation {
}
