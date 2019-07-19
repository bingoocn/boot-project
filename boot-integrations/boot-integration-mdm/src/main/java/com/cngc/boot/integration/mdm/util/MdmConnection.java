package com.cngc.boot.integration.mdm.util;

import com.cngc.boot.integration.mdm.property.MdmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author:viking
 * @date:2019/4/8 16:06
 */
public class MdmConnection {
    private static final Logger log = LoggerFactory.getLogger(MdmConnection.class);

    private static java.sql.Connection sqlConnection;

    public static java.sql.Connection getConnection() {
        String driver = MdmProperties.driver;
        String url = MdmProperties.url;
        String user = MdmProperties.user;
        String pass = MdmProperties.pass;

        if (sqlConnection != null) {
            return sqlConnection;
        }

        try {
            Class.forName(driver);
            sqlConnection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            log.info("获取连接错误：{}", e.getMessage());
        }

        return sqlConnection;
    }
}
