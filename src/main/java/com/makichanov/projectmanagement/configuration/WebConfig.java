package com.makichanov.projectmanagement.configuration;

import com.makichanov.projectmanagement.converter.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.makichanov.projectmanagement.repository")
@PropertySource("classpath:db/database.properties")
public class WebConfig implements WebMvcConfigurer {

    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();
        converters.add(new CreatingProjectDtoToProjectConverter());
        converters.add(new CreatingTaskDtoToTaskConverter());
        converters.add(new CreatingUserDtoToUserConverter());
        converters.add(new ProjectToProjectDtoConverter());
        converters.add(new TaskToTaskDtoConverter());
        converters.add(new UserToUserDtoConverter());
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CreatingProjectDtoToProjectConverter());
        registry.addConverter(new CreatingTaskDtoToTaskConverter());
        registry.addConverter(new CreatingUserDtoToUserConverter());
        registry.addConverter(new ProjectToProjectDtoConverter());
        registry.addConverter(new TaskToTaskDtoConverter());
        registry.addConverter(new UserToUserDtoConverter());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
