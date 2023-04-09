package com.repill.was.global.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.repill")
public class DataSourceConfig {

	public static final String MASTER = "MASTER";
	public static final String SLAVE = "SLAVE";

	@Bean
	@ConfigurationProperties(prefix = "spring.master-datasource")
	public HikariConfig masterHikariConfig() {
		return new HikariConfig();
	}

	@Bean
	@ConditionalOnProperty(prefix = "spring.slave-datasource", name = "jdbcUrl")
	@ConfigurationProperties(prefix = "spring.slave-datasource")
	public HikariConfig slaveHikariConfig() {
		return new HikariConfig();
	}

	@Bean(destroyMethod = "close")
	public DataSource masterDataSource() {
		return new HikariDataSource(masterHikariConfig());
	}

	@Bean(name = "slaveDataSource", destroyMethod = "close")
	@ConditionalOnBean(name = "slaveHikariConfig")
	public DataSource slaveDataSource() {
		return new HikariDataSource(slaveHikariConfig());
	}

	@Bean(name = "routingDataSource")
	@ConditionalOnBean(name = "slaveDataSource")
	public DataSource routingDataSource() {
		AbstractRoutingDataSource dataSource = new AbstractRoutingDataSource() {

			@Override
			protected Object determineCurrentLookupKey() {
				return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? SLAVE : MASTER;
			}

		};
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(MASTER, masterDataSource());
		dataSourceMap.put(SLAVE, slaveDataSource());
		dataSource.setTargetDataSources(dataSourceMap);
		dataSource.setDefaultTargetDataSource(masterDataSource());

		return dataSource;
	}

	@Bean
	@Primary
	@ConditionalOnBean(name = "routingDataSource")
	public DataSource dataSource() {
		return new LazyConnectionDataSourceProxy(routingDataSource());
	}
}
