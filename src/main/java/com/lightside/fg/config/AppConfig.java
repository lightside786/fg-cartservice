package com.lightside.fg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.lightside.fg.repository")
@ComponentScan(basePackages = "com.lightside.fg")
@Slf4j
public class AppConfig {

    @Configuration
    @Profile("cloud")
    static class CloudConfig extends AbstractCloudConfig {

        private static final int DEFAULT_MIN_POOL_SIZE = 4;
        private static final int DEFAULT_MAX_POOL_SIZE = 4;
        private static final int DEFAULT_CONNECTION_WAIT_TIME = 3000;

        @Autowired
        private Environment environment;

        @Bean
        @Primary
        public DataSource fgCartDataSource() {
            log.debug("Creating data source");
            final int minPoolSize = getConnectionPoolProperty("service.datasource.initial-size", DEFAULT_MIN_POOL_SIZE);
            final int maxPoolSize = getConnectionPoolProperty("service.datasource.max-active", DEFAULT_MAX_POOL_SIZE);
            final int maxWait = getConnectionPoolProperty("service.datasource.maxwait.millis", DEFAULT_CONNECTION_WAIT_TIME);
            log.info("Service data source details-> minPool :{}, maxPool:{}, maxWait:{}", minPoolSize, maxPoolSize, maxWait);
            return createDataSource(minPoolSize, maxPoolSize, maxWait);
        }

        private Integer getConnectionPoolProperty(String propertyName, int defaultValue) {
            return environment.getProperty(propertyName, Integer.class, defaultValue);
        }

        private DataSource createDataSource(int minPoolSize, int maxPoolSize, int maxWait) {
            PooledServiceConnectorConfig.PoolConfig poolConfig = new PooledServiceConnectorConfig.PoolConfig(minPoolSize, maxPoolSize, maxWait);
            DataSourceConfig dbConfig = new DataSourceConfig(poolConfig, null);
            return connectionFactory().dataSource(dbConfig);
        }
    }


    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setCacheSeconds(3600); //TODO read from properties
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
