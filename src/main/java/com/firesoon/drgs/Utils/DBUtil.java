package com.firesoon.drgs.Utils;

import com.firesoon.drgs.dto.base.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author create by yingjie.chen on 2018/1/16.
 * @version 2018/1/16 10:54
 */
public class DBUtil {

    static Map<String, JdbcTemplate> jdbcMap = new HashMap<String, JdbcTemplate>();

    public static JdbcTemplate getJdbcTemplate(DataSource dataSource) {

        String key = StringUtils.join(
                new String[]{dataSource.getClassname(), dataSource.getUrl(),
                        dataSource.getUsername(), dataSource.getPassword()},
                "|");
        JdbcTemplate connection = jdbcMap.get(key);
        if (connection != null) {
            return connection;
        } else {
            BasicDataSource basicdatasource = new BasicDataSource();
            basicdatasource.setDriverClassName(dataSource.getClassname());
            basicdatasource.setUrl(dataSource.getUrl());
            basicdatasource.setUsername(dataSource.getUsername());
            basicdatasource.setPassword(dataSource.getPassword());
            basicdatasource.setMaxTotal(3);
            connection = new JdbcTemplate(basicdatasource);
            jdbcMap.put(key, connection);
            return connection;
        }
    }

    public static DataSource getDataSource(String type) {
        DataSource ds = new DataSource();
        if ("drgs".equals(type)) {
            ds.setClassname(PropertiesUtil.properties.getProperty("spring.datasource.driver-class-name"));
            ds.setUrl(PropertiesUtil.properties.getProperty("spring.datasource.url"));
            ds.setUsername(PropertiesUtil.properties.getProperty("spring.datasource.username"));
            ds.setPassword(PropertiesUtil.properties.getProperty("spring.datasource.password"));
        }
        return ds;
    }
}
