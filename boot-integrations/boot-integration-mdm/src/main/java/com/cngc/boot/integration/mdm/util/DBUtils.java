package com.cngc.boot.integration.mdm.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:viking
 * @date:2019/4/8 15:10
 */
public class DBUtils {

    private static final Logger log = LoggerFactory.getLogger(DBUtils.class);

    /**
     * @param conn 数据源链接
     * @param sql  查询语句
     * @return List<Map>
     */
    public static List execQuery(Connection conn, String sql) {
        Statement st = null;
        ResultSet rs = null;
        List result = null;
        try {
            result = new ArrayList();
            st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);    //执行最初始的SQL
            ResultSetMetaData metaDate = rs.getMetaData();

            while (rs.next()) {
                Map record = new HashMap();
                for (int i = 1; i <= metaDate.getColumnCount(); i++) {
                    // 获得指定列的列名
                    String columnName = metaDate.getColumnName(i);
                    // 获得指定列的列值
                    String columnValue = rs.getString(i);
                    //放入集合
                    record.put(columnName, columnValue);
                }
                result.add(record);
            }
        } catch (SQLException e) {
            log.info("SQL执行查询异常:{}", e.toString());
            e.printStackTrace();
        } finally {
            DBUtils.closeResultset(rs);
            DBUtils.closeStatement(st);
            //DBUtils.closeConn(conn);
        }
        return result;
    }

    /**
     * @param sql 查询语句
     * @return List<Map>
     */
    public static List execQuery(String sql) {
        return execQuery(MdmConnection.getConnection(), sql);
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConn(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            log.info("关闭连接异常:{}", e.toString());
        }
    }

    /**
     * 关闭
     *
     * @param stmt
     */
    public static void closeStatement(Statement stmt) {
        if (stmt == null) {
            return;
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            log.info("关闭PS异常:{}", e.toString());
        }
    }

    /**
     * 关闭
     *
     * @param rs
     */
    public static void closeResultset(ResultSet rs) {
        if (rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (SQLException e) {
            log.info("关闭RS异常:{}", "e.toString()");
        }
    }

    public static <T> T queryBean(String sql, Class<T> beanClass) throws SQLException,
            InstantiationException, IllegalAccessException {
        return queryBean(MdmConnection.getConnection(), sql, beanClass);
    }

    public static <T> T queryBean(Connection con, String sql, Class<T> beanClass) throws SQLException,
            InstantiationException, IllegalAccessException {
        List<T> lists = queryBeanList(con, sql, beanClass);
        if (lists.size() != 1) {
            throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");
        }
        return lists.get(0);
    }

    public static <T> List<T> queryBeanList(Connection con, String sql, Class<T> beanClass) throws SQLException,
            InstantiationException, IllegalAccessException {
        List<T> lists = new ArrayList<T>();
        Statement stmt = null;
        ResultSet rs = null;
        Field[] fields = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            fields = beanClass.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
            }
            while (null != rs && rs.next()) {
                T t = beanClass.newInstance();
                for (Field f : fields) {
                    String name = f.getName();
                    try {
                        Object value = rs.getObject(name);
                        setValue(t, f, value);
                    } catch (Exception e) {
                    }
                }
                lists.add(t);
            }
        } finally {
            if (null != rs) {
                rs.close();
            }
            if (null != stmt) {
                stmt.close();
            }
        }
        return lists;
    }

    private static <T> void setValue(T t, Field f, Object value) throws IllegalAccessException {
        // TODO 以数据库类型为准绳，还是以java数据类型为准绳？还是混合两种方式？
        if (null == value) {
            return;
        }
        String v = value.toString();
        String n = f.getType().getName();
        if ("java.lang.Byte".equals(n) || "byte".equals(n)) {
            f.set(t, Byte.parseByte(v));
        } else if ("java.lang.Short".equals(n) || "short".equals(n)) {
            f.set(t, Short.parseShort(v));
        } else if ("java.lang.Integer".equals(n) || "int".equals(n)) {
            f.set(t, Integer.parseInt(v));
        } else if ("java.lang.Long".equals(n) || "long".equals(n)) {
            f.set(t, Long.parseLong(v));
        } else if ("java.lang.Float".equals(n) || "float".equals(n)) {
            f.set(t, Float.parseFloat(v));
        } else if ("java.lang.Double".equals(n) || "double".equals(n)) {
            f.set(t, Double.parseDouble(v));
        } else if ("java.lang.String".equals(n)) {
            f.set(t, value.toString());
        } else if ("java.lang.Character".equals(n) || "char".equals(n)) {
            f.set(t, (Character) value);
        } else if ("java.lang.Date".equals(n)) {
            f.set(t, new java.util.Date(((java.sql.Date) value).getTime()));
        } else if ("java.lang.Timer".equals(n)) {
            f.set(t, new Time(((Time) value).getTime()));
        } else if ("java.sql.Timestamp".equals(n)) {
            f.set(t, (java.sql.Timestamp) value);
        } else if ("java.math.BigDecimal".equals(n)) {
            f.set(t, (java.math.BigDecimal) new BigDecimal(value+""));
        } else {
            System.out.println("SqlError：暂时不支持此数据类型，请使用其他类型代替此类型！");
        }
    }
}
