package com.homework.Dev_SpringBoot_HW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com/homework/Dev_SpringBoot_HW"})
public class DevSpringBootHwApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevSpringBootHwApplication.class, args);
	}
}
