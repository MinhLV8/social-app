package com.minhlv.socialappapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataSource {

	@Autowired
	private ConfigApplicationUtil config;

	@Bean
	protected HikariDataSource getHikariDataSource() {
		try {
			HikariConfig jdbcConfig = new HikariConfig();
			jdbcConfig.setPoolName("SocialApp");
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
			return new HikariDataSource(jdbcConfig);

		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

}
