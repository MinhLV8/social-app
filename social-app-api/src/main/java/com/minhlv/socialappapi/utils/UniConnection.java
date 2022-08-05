package com.minhlv.socialappapi.utils;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UniConnection {

    private Connection conn;

    private List<Statement> stmts = new ArrayList<>();
    // private long startTime = System.currentTimeMillis();
    private ConfigApplicationUtil config = new ConfigApplicationUtil();

    private Map<String, String> monitor = new HashMap<>();
    private HikariDataSource hikariDataSource = null;

    private HikariDataSource getHikariDataSource() {
        try {
            if (hikariDataSource == null) {
                HikariConfig jdbcConfig = new HikariConfig();
                jdbcConfig.setPoolName("DynData");
                jdbcConfig.setMaximumPoolSize(20);
                jdbcConfig.setMinimumIdle(10);
                jdbcConfig.setIdleTimeout(30000);
                jdbcConfig.setConnectionTimeout(20000);
                jdbcConfig.setMaxLifetime(60000);
                jdbcConfig.setAutoCommit(true);
                jdbcConfig.setJdbcUrl(Utils.get(config.getValue(), "spring.datasource.url"));
                jdbcConfig.setUsername(Utils.get(config.getValue(), "spring.datasource.username"));
                jdbcConfig.setPassword(Utils.get(config.getValue(), "spring.datasource.password"));
                jdbcConfig.setConnectionTestQuery("select 1");
                jdbcConfig.addDataSourceProperty("cachePrepStmts", true);
                jdbcConfig.addDataSourceProperty("prepStmtCacheSize", 250);
                jdbcConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
                jdbcConfig.addDataSourceProperty("useServerPrepStmts", true);
                jdbcConfig.addDataSourceProperty("setTestOnReturn", true);
                jdbcConfig.addDataSourceProperty("setTestOnBorrow", true);
                jdbcConfig.addDataSourceProperty("setTestWhileIdle", true);
                hikariDataSource = new HikariDataSource(jdbcConfig);
                // kích hoạt monitor
                Thread th = new Thread(new MonitorThread());
                th.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hikariDataSource;
    }

}
