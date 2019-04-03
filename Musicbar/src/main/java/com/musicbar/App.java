package com.musicbar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.annotation.ApplicationScope;

import com.musicbar.core.utils.PinYinDemo;
import com.musicbar.core.utils.StringUtil;



@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling 
@MapperScan("com.musicbar.second.mapper") 
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
	}
}