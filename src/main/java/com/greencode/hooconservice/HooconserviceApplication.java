package com.greencode.hooconservice;

import com.greencode.hooconservice.Database.MLabDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class HooconserviceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(HooconserviceApplication.class, args);
	}
}
