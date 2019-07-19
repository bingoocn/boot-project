package com.cngc.boot.integration.r1.portal.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * r1 portal相关配置项.
 *
 * @author maxD
 */
@Data
@Component
@ConfigurationProperties("resourceone.portal")
public class R1PortalProperties {
    private DataSourceProperties resourceoneDatasource;
    private String resourceOneCoreConfigPath = "config";
    private String resourceOneAppConfigPath = "config";
    private String resourceOneHome = "/ResourceOneHome";
    @Value("${resourceone.portal.sso-client.except-url-pattern:\\.xml$}")
    private String ssoClientExceptUrlPattern;
    @Value("${resourceone.portal.right-filter.except-url-pattern:\\.xml$}")
    private String rightFilterExceptUrlPattern;
}
