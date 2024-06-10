//package com.aj.blogapp;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import jakarta.activation.DataSource;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import java.sql.DriverManager;
//
//@Configuration
//public class JpaTestConfig {
//
//	@Bean
//	@Profile("test")
//	public DriverManagerDataSource dataSource() {
//		var dataSource = new DriverManagerDataSource();
//
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUrl("jdbc:h2:mem:test;BDB_CLONE_DECAY=-1");
//
//		return dataSource;
//
//	}
//
//
//}
