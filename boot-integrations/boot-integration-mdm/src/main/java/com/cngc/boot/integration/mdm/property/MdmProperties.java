package com.cngc.boot.integration.mdm.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author:viking
 * @date:2019/4/8 16:07
 */
@Component
public class MdmProperties {

    public static String driver;
    public static String url;
    public static String user;
    public static String pass;

    @Autowired
    public MdmProperties getMdmProperties(@Value("${mdm.datasource.driver-class-name}") String driver,
                                        @Value("${mdm.datasource.url}") String url,
                                        @Value("${mdm.datasource.username}") String user,
                                        @Value("${mdm.datasource.password}") String pass
    ) {
        MdmProperties.driver = driver;
        MdmProperties.url = url;
        MdmProperties.user = user;
        MdmProperties.pass = pass;
        return this;
    }
}
