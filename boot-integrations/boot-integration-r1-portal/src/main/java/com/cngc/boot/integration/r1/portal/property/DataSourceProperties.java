package com.cngc.boot.integration.r1.portal.property;

import lombok.Data;

/**
 * r1 portal相关数据源配置项.
 *
 * @author maxD
 */
@Data
public class DataSourceProperties {
    private String url;
    private String userName;
    private String password;
    private String driverClassName;
}
