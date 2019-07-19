package com.cngc.boot.integration.r1.portal.config;

import com.cngc.boot.integration.r1.portal.property.R1PortalProperties;
import com.icss.resourceone.sdk.SdkFilter;
import com.icss.resourceone.sdk.init.InitR1ClientServlet;
import com.icss.resourceone.sdk.right.RightFilter;
import com.icss.resourceone.sso.client.R1ClientService;
import com.icss.resourceone.sso.client.SSOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 集成到r1 portal所需配置信息.
 *
 * @author maxD
 */
@Configuration
@ComponentScan("com.cngc.boot.integration.r1.portal")
public class R1IntegrationConfigs {
    @Autowired
    private R1PortalProperties r1PortalProperties;

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("resourceOneCoreConfigPath", r1PortalProperties.getResourceOneCoreConfigPath());
            servletContext.setInitParameter("resourceOneAppConfigPath", r1PortalProperties.getResourceOneAppConfigPath());
            servletContext.setInitParameter("resourceOneHome", r1PortalProperties.getResourceOneHome());
        };
    }

    @Bean
    public ServletRegistrationBean initR1ClientServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new InitR1ClientServlet(), false);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setName("InitR1ClientServlet");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean r1ClientService(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new R1ClientService(), "/R1ClientService");
        servletRegistrationBean.setName("R1ClientService");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean ssoClient(){
        FilterRegistrationBean registration = new FilterRegistrationBean<>(new SSOClient());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("_except_urlpattern",r1PortalProperties.getSsoClientExceptUrlPattern());
        registration.addInitParameter("URIEncoding","utf-8");
        registration.setName("SSOClient");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean sdkFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean<>(new SdkFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("SdkFilterType","client");
        registration.setName("SdkFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean rightFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean<>(new RightFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("_except_urlpattern",r1PortalProperties.getRightFilterExceptUrlPattern());
        registration.setName("RightFilter");
        registration.setOrder(3);
        return registration;
    }
}
