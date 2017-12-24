package com.augment.spring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "com.augment")
@PropertySource("classpath:config.properties")
public class RootContextConfiguration {

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUserName;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean
    public BasicDataSource basicDataSource() {
        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(jdbcDriverClassName);
        ds.setUrl(jdbcUrl);
        ds.setUsername(jdbcUserName);
        ds.setPassword(jdbcPassword);
        ds.setInitialSize(1);
        ds.setMaxTotal(2);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(basicDataSource());
    }
}
