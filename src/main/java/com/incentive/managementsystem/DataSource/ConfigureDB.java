package com.incentive.managementsystem.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.security.SecureRandom;

@Configuration
@ComponentScan(basePackages="net.codejava.spring")
@EnableWebMvc
public class ConfigureDB extends WebMvcConfigurerAdapter{

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://35.222.99.141:3306/incentive");
        dataSource.setUsername("group");
        dataSource.setPassword("admin123123");

        return dataSource;
    }*/


}