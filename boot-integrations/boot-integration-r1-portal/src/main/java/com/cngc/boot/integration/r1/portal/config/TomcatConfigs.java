package com.cngc.boot.integration.r1.portal.config;

import com.cngc.boot.integration.r1.portal.property.R1PortalProperties;
import com.cngc.boot.integration.r1.portal.property.DataSourceProperties;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 处理r1sdk所需数据源,存储到jndi中.
 *
 * @author maxD
 */
@Configuration
public class TomcatConfigs {
    @Autowired
    private R1PortalProperties r1PortalProperties;

    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }
            @Override
            protected void postProcessContext(Context context) {
                // 配置r1portal数据源
                ContextResource r1DataSource= new ContextResource();
                r1DataSource.setType(DataSource.class.getName());
                r1DataSource.setName("ResourceOne/DataSource");
                DataSourceProperties r1DataSourceProperties = r1PortalProperties.getResourceoneDatasource();
                r1DataSource.setProperty("driverClassName", r1DataSourceProperties.getDriverClassName());
                r1DataSource.setProperty("url", r1DataSourceProperties.getUrl());
                r1DataSource.setProperty("username", r1DataSourceProperties.getUserName());
                r1DataSource.setProperty("password", r1DataSourceProperties.getPassword());
                context.getNamingResources().addResource(r1DataSource);
            }
        };
    }
}
