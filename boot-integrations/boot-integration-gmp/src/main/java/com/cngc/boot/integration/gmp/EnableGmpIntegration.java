package com.cngc.boot.integration.gmp;

import com.cngc.boot.core.annotation.EnableCngcBoot;
import com.cngc.boot.integration.gmp.config.IntegrationConfiguration;
import com.cngc.boot.security.annotation.EnableCngcSecurity;
import com.cngc.boot.web.annotation.EnableCngcWebMvc;
import org.jeecg.common.system.sdk.annotation.EnableGmpSdk;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启gmp平台集成.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableCngcBoot
@EnableCngcSecurity
@EnableCngcWebMvc
@EnableGmpSdk
@Import(IntegrationConfiguration.class)
public @interface EnableGmpIntegration {
}
