package org.clickhouse.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceConnection {
    private static final String url = "jdbc:clickhouse://127.0.0.1:8123/sport";
//    private static final String username = "ekaterina";
//    private static final String password = "root";


    public static DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("ru.yandex.clickhouse.ClickHouseDriver");
        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
        return dataSource;
    }
}
