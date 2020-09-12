package com.ilegra.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ilegra.sales.utils.DirectoryUtil;

@SpringBootApplication
@EnableScheduling
public class SalesApplication {

	public static void main(String[] args) {
		DirectoryUtil.checkDirectories();
		SpringApplication.run(SalesApplication.class, args);
	}
}
